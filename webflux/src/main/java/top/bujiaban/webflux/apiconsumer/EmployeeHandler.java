package top.bujiaban.webflux.apiconsumer;

import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.bujiaban.webflux.dto.Employee;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class EmployeeHandler {
    private WebClient webClient;

    public EmployeeHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ServerResponse> showEmployee(ServerRequest request) {
        Mono<Employee> result = webClient.get()
                .uri("/employees/{id}", request.pathVariable("id"))
                .retrieve()
                .bodyToMono(Employee.class);

        Mono<Employee> resultWithHystrix = HystrixCommands.from(result)
                .commandName("showEmployee")
                .toMono();
        return ok().body(resultWithHystrix, Employee.class);
    }

    public Mono<ServerResponse> listEmployees(ServerRequest request) {
        Flux<Employee> result = webClient.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);
        return ok().body(result.collectList(), Employee.class);
    }
}
