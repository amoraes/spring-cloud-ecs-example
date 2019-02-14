package com.github.amoraes.custommicroservicestarter.autoconfiguration;

import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@ConditionalOnClass(CacheAutoConfiguration.class)
public class CachingAutoConfiguration {
}
