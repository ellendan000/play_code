package top.bujiaban.webflux.apiprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.bujiaban.webflux.dto.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    Mono<Employee> getEmployee(@PathVariable Long id) {
        return Mono.just(Employee.builder()
                .id(id)
                .name("zhang san")
                .build());
    }

    @GetMapping
    Flux<Employee> getAllEmployee() {
        return Flux.just(
                Employee.builder()
                        .id(1L)
                        .name("zhang san 1")
                        .build(),
                Employee.builder()
                        .id(2L)
                        .name("zhang san 2")
                        .build()

        );
    }


}
