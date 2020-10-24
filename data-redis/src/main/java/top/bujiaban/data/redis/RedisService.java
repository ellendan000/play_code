package top.bujiaban.data.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private RedisTemplate<String, String> template;

    public RedisService(RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void testPerformance(){
        String name = "test";
        String key = "key";
        String value = "change";
        long start = System.currentTimeMillis();

//        template.Hash().putIfAbsent(name, key, value);
        template.opsForHash().get(name, key);
        System.out.println(System.currentTimeMillis() - start);
    }
}

