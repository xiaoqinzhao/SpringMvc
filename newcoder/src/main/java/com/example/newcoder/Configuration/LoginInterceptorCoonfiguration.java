package com.example.newcoder.Configuration;

import com.example.newcoder.intercepter.PassportIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class LoginInterceptorCoonfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public PassportIntercepter PassportIntercepter() {
        return new PassportIntercepter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(PassportIntercepter()).addPathPatterns("/question/home");
        super.addInterceptors(interceptorRegistry);
    }
}
