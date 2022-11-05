package bujiaban.top;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class KeyvaultDBConfiguration implements EnvironmentAware {
    private Environment environment;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
