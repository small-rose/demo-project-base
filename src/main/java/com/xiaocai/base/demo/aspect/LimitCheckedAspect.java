package com.xiaocai.base.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.xiaocai.base.demo.annotation.LimitChecked;
import com.xiaocai.base.demo.cache.MyCacheManager;
import com.xiaocai.base.demo.common.message.CommonResult;
import com.xiaocai.base.demo.exception.BizCommonException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;


/**
 * @author Xiaocai.Zhang
 */
@Slf4j
@Aspect
@Component
public class LimitCheckedAspect {

    private final String DEFAULT_NAME = "";
    private final String TIME_KEY = "limitTime";
    private final String TOTAL_KEY = "limitTotal";
    private final String WAIT_KEY = "limitWait";
    private static final HashMap<String, MyCacheManager> cache = new HashMap<>();

    @Order(1) // Order 代表优先级，数字越小优先级越高
    @Pointcut("@annotation(com.xiaocai.base.demo.annotation.LimitChecked)")
    public void checkedPoint(){};

    @Before(value = "checkedPoint()")
    public void checkBefore(){

        log.info("--checked before ");
    }

    @After(value = "checkedPoint()")
    public void checkAfter(){
        log.info("--checked After ");
    }

    @Around(value = "checkedPoint() && @annotation(limitChecked)")
    public CommonResult checkAround(ProceedingJoinPoint joinPoint, LimitChecked limitChecked){
        CommonResult commonResult = null;
        log.info("--checked before Around   ---");
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取入参
        Object[] param = joinPoint.getArgs();
        log.debug("methodName {},  param {}", methodName, param);

        // 注解拦截处理
        String nameKey = limitChecked.name();
        nameKey = DEFAULT_NAME.equals(nameKey) ?  methodName : nameKey ;
        long sec = limitChecked.second();
        // 注解验证无限制直接放行
        if (limitChecked.limit()){
            MyCacheManager cacheManager = cache.get(nameKey) ;
            long total = limitChecked.totalCount();
            long wait = limitChecked.limitWaiting();
            log.info("@LimitChecked params list: nameKey = {} , total = {},  sec = {},  wait = {}", nameKey, total, sec, wait);

            if ( null == cacheManager){
                cacheManager = new MyCacheManager(sec);
                cache.put(nameKey, cacheManager);
                log.info("Init cacheManager for api [ {} ], and the period is {} ", nameKey, sec);
            }
            if (limitChecked.waitEnable() && null != cache.get(nameKey).get(WAIT_KEY)){
                log.info("get wait value : {}", cache.get(nameKey).get(WAIT_KEY));
                return CommonResult.fail("the [ "+nameKey+" ] API limit, please call it after wait "+wait+" sec") ;
            }
            long expiryTime = sec * 1000 ;
            if (null == cache.get(nameKey).get(TIME_KEY)){
                LocalDateTime newLimit = LocalDateTime.now().plus(sec, ChronoUnit.SECONDS);
                cache.get(nameKey).put(TIME_KEY, newLimit, expiryTime);
                cache.get(nameKey).put(TOTAL_KEY, total, expiryTime);
                log.debug("put value -- key : {} , value {} ", TIME_KEY, newLimit);
                log.debug("put value -- key : {} , value {} ", TOTAL_KEY, total);
            }

            LocalDateTime limitTime = (LocalDateTime) cache.get(nameKey).get(TIME_KEY);
            long newTotal = (long)cache.get(nameKey).get(TOTAL_KEY);
            log.debug("limit newTotal -- key : {} , value {} ", TOTAL_KEY, newTotal);

            long waitTime = wait * 1000 ;
            // 其实该种情况理论上不会出现，因为 limitTime 在 sec 之后就会过期，如果限制时间过期，其实不会比拿出比现在小的时间
            if (limitTime.isBefore(LocalDateTime.now()) ){
                Object nothing = limitChecked.waitEnable() ? cache.get(nameKey).put(WAIT_KEY, wait, waitTime) : null;
                return CommonResult.fail("the [ "+nameKey+" ] API limit, The time window has reached the limit time of expiry time") ;
            }
            if ( newTotal <= 0 ){
                if (limitChecked.waitEnable()){
                    cache.get(nameKey).put(WAIT_KEY, wait, waitTime);
                    log.info("wait {} ", wait);
                }
                return CommonResult.fail("the [ "+nameKey+" ] API limit, The time window has reached the limit number of times ["+total+"]") ;
            }
            if (!limitChecked.onlySuccessCount()){
                newTotal = newTotal - 1 ;
                cache.get(nameKey).put(TOTAL_KEY, newTotal, expiryTime);
                log.debug("--put new total value -- key : {} , value {}", TOTAL_KEY, newTotal);
            }
        }
        // 继续执行请求方法
        try {
            commonResult = (CommonResult) joinPoint.proceed();
        }catch (BizCommonException e) {
            return CommonResult.fail("api occurred exception ...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }

        log.info("--checked after Around   ---");

        // 注解限制生效前提下，开启接口调用成功才计数
        if (limitChecked.limit() && limitChecked.onlySuccessCount()){
            long newTotal = (long)cache.get(nameKey).get(TOTAL_KEY);
            newTotal = newTotal - 1 ;
            cache.get(nameKey).put(TOTAL_KEY, newTotal, sec * 1000);
            log.debug("--put new total value -- key : {} , value {}", TOTAL_KEY, newTotal);
        }

        return  commonResult == null ? CommonResult.fail("API Error "): commonResult ;
    }

    @AfterReturning(value = "checkedPoint()", returning = "result")
    public void checkAfterReturn(CommonResult result){
        log.info("--checked after return   ---");
        log.info("result : {}", JSONObject.toJSONString(result));
    }


    @AfterThrowing(value = "checkedPoint()", throwing="throwable")
    public CommonResult afterThrow(Throwable throwable){
        log.info("--checked  afterThrow ");
        return CommonResult.fail("AfterThrowing api occurred exception ...");
    }

}
