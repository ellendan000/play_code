package top.bujiaban.netflixcloud;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@EnableFeignClients
@SpringCloudApplication
public class NetflixApp {
    public static void main(String[] args) {
        SpringApplication.run(NetflixApp.class, args);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public NewTopic userTopic() {
        return new NewTopic("userTopic", 1, (short) 1);
    }
}
