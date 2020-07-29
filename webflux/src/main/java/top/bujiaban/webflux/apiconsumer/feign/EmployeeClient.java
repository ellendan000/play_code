package top.bujiaban.webflux.apiconsumer.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.bujiaban.webflux.dto.Employee;

public interface EmployeeClient {

    @GetMapping("/employees/{id}")
    Mono<Employee> showEmployee(@PathVariable String id);

    @GetMapping("/employees")
    Flux<Employee> listEmployees();
}
