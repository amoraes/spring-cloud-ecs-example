package com.github.amoraes.gateway.configuration;

import com.github.amoraes.gateway.web.APILoggingInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulHandlerBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    private APILoggingInterceptor apiLoggingInterceptor;

    public ZuulHandlerBeanPostProcessor(APILoggingInterceptor apiLoggingInterceptor) {
        this.apiLoggingInterceptor = apiLoggingInterceptor;
    }

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {

        if (bean instanceof ZuulHandlerMapping) {

            ZuulHandlerMapping zuulHandlerMapping = (ZuulHandlerMapping) bean;
            zuulHandlerMapping.setInterceptors(apiLoggingInterceptor);
        }

        return super.postProcessAfterInstantiation(bean, beanName);
    }

}