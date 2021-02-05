package top.bujiaban.openjava.future;

import java.util.Properties;

import static top.bujiaban.openjava.future.Configuration.THREAD;

public class Main {

    public static FutureGetter ioc(){
        Properties properties = new Properties();
        properties.setProperty(THREAD, String.valueOf(10));

        Configuration configuration = new Configuration(properties);
        return configuration.createFutureGetter();
    }

    public static void main(String[] args) {
        System.out.println("hello jenkins");
    }
}
