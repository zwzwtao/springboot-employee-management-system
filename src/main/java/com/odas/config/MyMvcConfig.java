package com.odas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 这里自己定义视图解析器，输入/或者/index.html跳转到templates下的login页面
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 自定义的国际化组件加在这里哟
    // 这里我们在configuration自己配置了LocaleResolver所以springboot就会自动去装配他
    // 进行国际化，源码里已经配置好里所以这个方法必须返回LocalResolver类型才能实现国际化自定义
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")     // 所有请求都拦截
                .excludePathPatterns("/index.html", "/", "/user/login", "/static/**");  // 排除一些路径的拦截，包括静态资源
    }
}
