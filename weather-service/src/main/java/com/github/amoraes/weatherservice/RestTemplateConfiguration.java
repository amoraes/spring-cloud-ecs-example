package com.github.amoraes.weatherservice;

import brave.spring.web.TracingClientHttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    private final TracingClientHttpRequestInterceptor tracingClientHttpRequestInterceptor;

    public RestTemplateConfiguration(TracingClientHttpRequestInterceptor tracingClientHttpRequestInterceptor) {
        this.tracingClientHttpRequestInterceptor = tracingClientHttpRequestInterceptor;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(tracingClientHttpRequestInterceptor);
        return restTemplate;
    }
}
