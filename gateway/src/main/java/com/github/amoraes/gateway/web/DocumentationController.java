package com.github.amoraes.gateway.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class DocumentationController implements SwaggerResourcesProvider {

	private DiscoveryClientRouteLocator discoveryClientRouteLocator;

	public DocumentationController(DiscoveryClientRouteLocator discoveryClientRouteLocator) {
		this.discoveryClientRouteLocator = discoveryClientRouteLocator;
	}

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<Route> routes = discoveryClientRouteLocator.getRoutes();
		for (Route route : routes) {
			resources.add(swaggerResource(route.getPrefix(), route.getPrefix() + "/docs" , "2.0"));
		}
		return resources;
	}



	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}