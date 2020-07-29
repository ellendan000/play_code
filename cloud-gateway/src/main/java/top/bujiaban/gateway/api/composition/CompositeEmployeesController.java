package top.bujiaban.gateway.api.composition;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import top.bujiaban.gateway.api.dto.Employee;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;


@RestController
public class CompositeEmployeesController {

    @GetMapping("/composite/employees")
    public Mono<ResponseEntity<List<Employee>>> proxy(ProxyExchange<byte[]> proxy) throws Exception {
        Mono<ResponseEntity<byte[]>> r1 = proxy.uri("lb://webflux/employees/1").get();
        Mono<ResponseEntity<byte[]>> r2 = proxy.uri("lb://webflux/employees").get();
        return Mono.zip(r1, r2).map(composite());
    }

    private Function<Tuple2<ResponseEntity<byte[]>, ResponseEntity<byte[]>>, ResponseEntity<List<Employee>>> composite() {
        final ObjectMapper mapper = new ObjectMapper();
        return tuple2 -> {
            try {
                Employee employee = mapper.readValue(tuple2.getT1().getBody(), Employee.class);
                List<Employee> employees = mapper.readValue(tuple2.getT2().getBody(), new TypeReference<List<Employee>>(){});
                employees.add(employee);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(employees);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        };
    }
}