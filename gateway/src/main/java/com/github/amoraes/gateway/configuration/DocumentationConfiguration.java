package com.github.amoraes.gateway.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Component
public class DocumentationConfiguration {

	private static final String SHOW_DOCS_KEY = "show-docs";

	private final GatewayProperties gatewayProperties;
	private final SwaggerUiConfigProperties swaggerUiConfigProperties;

	private final List<GroupedOpenApi> groups = new ArrayList<>();

	public DocumentationConfiguration(GatewayProperties gatewayProperties, SwaggerUiConfigProperties swaggerUiConfigProperties) {
		this.gatewayProperties = gatewayProperties;
		this.swaggerUiConfigProperties = swaggerUiConfigProperties;
	}

	@Bean
	@Lazy(false)
	public List<GroupedOpenApi> apis() {
		if (CollectionUtils.isEmpty(groups)) {
			generateSwaggerRoutes();
		}
		return groups;
	}

	private void generateSwaggerRoutes() {
		swaggerUiConfigProperties.setUrls(new HashSet<>());
		List<RouteDefinition> routes = new ArrayList<>(gatewayProperties.getRoutes());
		routes.sort(Comparator.comparing(RouteDefinition::getId));
		routes.forEach(routeDefinition -> {
			String service = routeDefinition.getId();
			if (routeDefinition.getMetadata() != null && Boolean.TRUE.equals(routeDefinition.getMetadata().get(SHOW_DOCS_KEY))) {
				AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
				swaggerUrl.setName(service);
				swaggerUrl.setDisplayName(service);
				swaggerUrl.setUrl("/docs/" + service);
				swaggerUiConfigProperties.getUrls().add(swaggerUrl);
				groups.add(GroupedOpenApi.builder().displayName(service).pathsToMatch("/**").group(service).build());
			}
		});
	}

	@EventListener(RefreshScopeRefreshedEvent.class)
	public void onRefresh(RefreshScopeRefreshedEvent event) {
		generateSwaggerRoutes();
	}

}