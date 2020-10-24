package top.bujiaban.data.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private RedisService redisService;

    public TestController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/get")
    public void get() {
        redisService.testPerformance();
    }
}
