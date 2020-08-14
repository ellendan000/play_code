package top.bujiaban.netflixcloud.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStoppedEvent;

public class WireMockContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        WireMockServer server = new WireMockServer(8081);
        server.start();

        applicationContext.getBeanFactory().registerSingleton("wireMockServer", server);
        applicationContext.addApplicationListener(event ->{
            if(event instanceof ContextStoppedEvent) {
                server.stop();
            }
        });
    }
}
