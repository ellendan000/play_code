package top.bujiaban.pinpoint;

import org.springframework.stereotype.Component;
import top.bujiaban.pinpoint.async.SayHelloTask;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class ForAsyncFilter implements Filter {
    private Executor executor = Executors.newFixedThreadPool(5);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            executor.execute(new SayHelloTask("GGG"));
        }
    }
}
