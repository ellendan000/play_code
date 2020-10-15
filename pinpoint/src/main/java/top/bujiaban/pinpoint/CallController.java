package top.bujiaban.pinpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CallController {
    private CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping(path="/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String callHttp(@PathVariable String id) {
        return callService.readUsername(id);
    }

    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String callMQ(@RequestBody Map<String, String> user) {
        return callService.writeUser(user.get("username"));
    }
}
