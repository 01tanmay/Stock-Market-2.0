package com.stock.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Primary
@Configuration
@EnableSwagger2
public class SwaggerConfig implements SwaggerResourcesProvider {

	@Autowired
	private RouteLocator routeLocator;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();

		routeLocator.getRoutes().stream().forEach(route -> {
			resources.add(addSwaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "2.0"));
		});
		return resources;
	}

	private SwaggerResource addSwaggerResource(final String name, final String location, final String swaggerVersion) {
		SwaggerResource resource = new SwaggerResource();
		resource.setName(name);
		resource.setLocation(location);
		resource.setSwaggerVersion(swaggerVersion);
		return resource;
	}

}
