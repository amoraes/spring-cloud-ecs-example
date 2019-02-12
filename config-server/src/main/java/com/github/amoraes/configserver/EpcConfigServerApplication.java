package com.github.amoraes.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableConfigServer
public class EpcConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpcConfigServerApplication.class, args);
	}

}
