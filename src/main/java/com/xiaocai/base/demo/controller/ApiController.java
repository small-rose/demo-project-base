package com.xiaocai.base.demo.controller;

import com.xiaocai.base.demo.annotation.ApiChecked;
import com.xiaocai.base.demo.annotation.LimitChecked;
import com.xiaocai.base.demo.annotation.MyLog;
import com.xiaocai.base.demo.common.base.BaseController;
import com.xiaocai.base.demo.common.base.BaseService;
import com.xiaocai.base.demo.common.message.CommonResult;
import com.xiaocai.base.demo.exception.BizCommonException;
import com.xiaocai.base.demo.exception.ErrorCode;
import com.xiaocai.base.demo.service.base.UserBsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiaocai.Zhang
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Autowired
    private UserBsService userBsService;

    @Override
    public BaseService getBaseService() {
        return userBsService;
    }

    @RequestMapping("/check")
    @ApiChecked(second = 20, totalCount = 3)
    public CommonResult checkUser() {
        log.info("--@InterfaceChecked  normal  do business ...");
        return CommonResult.ok("@InterfaceChecked is normal, please watch console log.");
    }
    @RequestMapping("/check2")
    @ApiChecked(second = 15, totalCount = 5, limit = false)
    public CommonResult checkUser2() {
        log.info("--@InterfaceChecked limit false  business ...");
        return CommonResult.ok("@InterfaceChecked limit is false, please watch console log.");
    }

    @RequestMapping(value = {"/check3", "limitNormal"})
    @LimitChecked(second = 5, totalCount = 2)
    public CommonResult check3() {
        log.info("--limit normal do business ...");
        return CommonResult.ok("@LimitChecked limit is normal (true), please watch console log.");
    }

    @RequestMapping(value = {"/check4", "/limitFalse"})
    @LimitChecked(limit = false, second = 8, totalCount = 3)
    public CommonResult check4() {
        log.info("--limit false 该接口不进行限制 do business ...");
        return CommonResult.ok("@LimitChecked limit is false, please watch console log.");
    }

    @RequestMapping(value = {"/check5", "/limitWait"})
    @LimitChecked(second = 6, totalCount = 2, waitEnable = true, limitWaiting = 10)
    public CommonResult check5() {
        log.info("--limit normal  waitEnable do business ...");
        return CommonResult.ok("@LimitChecked waitEnable is  true, please watch console log.");
    }


    int temp = 0 ;
    @RequestMapping(value = {"/check6", "/limitSuccess"})
    @LimitChecked(second = 30, totalCount = 3, onlySuccessCount = true)
    public CommonResult check6() {
        temp++;
        log.info("--onlySuccessCount do business ...");
        if ( 2 == temp ){
            throw new BizCommonException(ErrorCode.SYSTEM_SERVICE_ERROR_CODE);
        }
        return CommonResult.ok("@LimitChecked onlySuccessCount is true , please watch console log.");
    }

    @RequestMapping("/checkLog")
    @MyLog
    public CommonResult checkLog() {
        log.info("--checkLog do business ...");
        return CommonResult.ok("@MyLog to checkLog, please watch console log.");
    }
}
