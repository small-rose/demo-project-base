package com.xiaocai.base.demo.config;

import com.xiaocai.base.demo.intercepter.ApiCheckedInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xiaocai.Zhang
 */
@Configuration
public class InterceptorTrainConfig implements WebMvcConfigurer {

    /**
     * 拦截器注册类 InterceptorRegistry 加入自己的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiCheckedInterceptor()).addPathPatterns("/api/**");
    }
}
