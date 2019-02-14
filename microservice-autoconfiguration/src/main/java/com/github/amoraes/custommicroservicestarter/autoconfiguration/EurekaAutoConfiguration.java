package com.github.amoraes.custommicroservicestarter.autoconfiguration;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.github.amoraes" })
public class EurekaAutoConfiguration {
}
