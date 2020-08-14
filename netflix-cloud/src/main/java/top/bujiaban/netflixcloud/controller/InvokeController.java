package top.bujiaban.netflixcloud.controller;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bujiaban.netflixcloud.client.RemoteClient;

@Slf4j
@RestController
@RequestMapping("/users")
public class InvokeController {
    private RemoteClient client;

    public InvokeController(RemoteClient client) {
        this.client = client;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> success(@PathVariable String id) {
        try {
            String name = client.name(id);
            return ResponseEntity.ok(name);
        } catch (HystrixRuntimeException e) {
            log.info(e.getMessage(), e);

            if (e.getCause() instanceof FeignException.NotFound) {
                log.info(e.getMessage(), e);
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/failed")
    public String failed() {
        try {
            return client.name("002");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}