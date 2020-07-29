package top.bujiaban.webflux.apiconsumer.feign;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.bujiaban.webflux.dto.Employee;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Component
public class EmployeeHystrixHandler {
    private EmployeeClient employeeClient;

    public EmployeeHystrixHandler(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    public Mono<ServerResponse> showEmployee(ServerRequest request) {
        Mono<Employee> result = employeeClient.showEmployee(request.pathVariable("id"));
        return ok().body(result, Employee.class);
    }

    public Mono<ServerResponse> listEmployees(ServerRequest request) {
        Flux<Employee> result = employeeClient.listEmployees();
        return ok().body(result.next(), Employee.class);
    }
}
