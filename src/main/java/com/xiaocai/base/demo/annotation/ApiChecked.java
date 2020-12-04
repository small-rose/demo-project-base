package com.xiaocai.base.demo.annotation;

import java.lang.annotation.*;

/**
 * @author Xiaocai.Zhang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiChecked {

    String name() default "default";

    boolean limit() default true;

    long second() default 0;

    long totalCount() default 0;

    boolean waitEnable() default false;

    long limitWaiting() default 3;
}
