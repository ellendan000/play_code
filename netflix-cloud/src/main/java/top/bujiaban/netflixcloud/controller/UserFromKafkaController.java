package top.bujiaban.netflixcloud.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserFromKafkaController {
    private Map<String, User> userMap = new HashMap();

    @GetMapping(path = "/cachedUsers/{id}/username")
    public String username(@PathVariable String id) {
        return userMap.get(id).getUsername();
    }

    @KafkaListener(id = "userGroup", topics = "userTopic")
    public void listen(User user) {
        userMap.put(user.getId(), user);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private String id;
        private String username;
    }
}