package top.bujiaban.sleuth;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class SleuthApp {
    public static void main(String[] args) {
        SpringApplication.run(SleuthApp.class, args);
    }

    @Bean
    public NewTopic userTopic() {
        return new NewTopic("userTopic", 1, (short) 1);
    }
}
