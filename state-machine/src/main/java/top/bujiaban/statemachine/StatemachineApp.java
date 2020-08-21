package top.bujiaban.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

@SpringBootApplication
@EnableStateMachine
public class StatemachineApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(StatemachineApp.class);
        application.run(args);
    }
}
