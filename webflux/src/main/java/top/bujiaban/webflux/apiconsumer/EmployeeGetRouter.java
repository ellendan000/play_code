package top.bujiaban.webflux.apiconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EmployeeGetRouter {
    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeaders(header -> header.setBasicAuth("user", "user"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes(EmployeeHandler employeeHandler) {
        return route(GET("/consumer/employees/{id}"), employeeHandler::showEmployee)
                .and(route(GET("/consumer/employees"), employeeHandler::listEmployees));
    }
}
