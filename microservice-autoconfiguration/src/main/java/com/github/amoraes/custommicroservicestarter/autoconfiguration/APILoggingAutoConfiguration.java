package com.github.amoraes.custommicroservicestarter.autoconfiguration;

import com.github.amoraes.custommicroservicestarter.APILoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class APILoggingAutoConfiguration implements WebMvcConfigurer {
 
    @Autowired
    private APILoggingInterceptor apiLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingInterceptor)
          .addPathPatterns("/**").excludePathPatterns("**/actuator/**");
    }
}