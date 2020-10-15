package top.bujiaban.pinpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CallService {
    private RemoteHttpClient httpClient;

    private KafkaTemplate<String, Object> template;

    public CallService(RemoteHttpClient httpClient, KafkaTemplate<String, Object> template) {
        this.httpClient = httpClient;
        this.template = template;
    }

    String readUsername(String id) {
        return httpClient.username(id);
    }

    String writeUser(String username) {
        String id = UUID.randomUUID().toString();
        template.send("userTopic", new User(id, username));
        return id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private String id;
        private String username;
    }
}
