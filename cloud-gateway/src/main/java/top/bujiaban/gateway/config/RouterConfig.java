package top.bujiaban.gateway.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class RouterConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("toBing", p -> p.path("/gogogo/**")
                        .filters(f -> f.rewritePath("/gogogo/(?<path>.*)", "/${path}"))
                        .uri("https://cn.bing.com"))
                .route("toWeb", p -> p.path("/to/{id}")
                        .uri("http://127.0.0.1:8080")
                )
                .build();
    }

}