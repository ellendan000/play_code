package top.bujiaban.openjava.future;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Configuration {
    public static final String THREAD = "thread";
    private Properties properties;

    public Configuration(Properties properties) {
        this.properties = properties;
    }

    public Executor initializeThreadPool() {
        return Executors.newFixedThreadPool(Integer.parseInt(properties.getProperty(THREAD)));
    }

    public FutureGetter createFutureGetter() {
        return new FutureGetter(initializeThreadPool());
    }
}
