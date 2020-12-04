package com.xiaocai.base.demo.annotation;

import java.lang.annotation.*;

/**
 * @author Xiaocai.Zhang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LimitChecked {
    /**
     * 为当前限制方法取个名字，默认是方法名
     * @return
     */
    String name() default "";

    /**
     * 方法限制标记，默认开启限制
     * @return
     */
    boolean limit() default true;

    /**
     * 方法限制一个时间窗口期，单位是秒
     * @return
     */
    long second() default 0;
    /**
     * 方法限制一个时间窗口期内限定次数
     * @return
     */
    long totalCount() default 0;
    /**
     * 是否开启限定后的等待延迟
     * @return
     */
    boolean waitEnable() default false;
    /**
     * 启限定后的等待延迟时间，仅在 waitEnable = true 的时候生效
     * @return
     */
    long limitWaiting() default 5;

    /**
     * 是否调用成功才计算次数
     * @return
     */
    boolean onlySuccessCount() default false;
}
