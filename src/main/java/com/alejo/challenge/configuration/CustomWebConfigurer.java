package com.alejo.challenge.configuration;

import com.alejo.challenge.loggers.InterceptLog;
import com.alejo.challenge.rate.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebConfigurer implements WebMvcConfigurer {

    @Autowired
    private InterceptLog logInterceptor;
    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/api/quotes/**");
    }
}