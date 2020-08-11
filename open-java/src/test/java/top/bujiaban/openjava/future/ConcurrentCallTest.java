package top.bujiaban.openjava.future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcurrentCallTest {
    private FutureGetter getter;
    private ConcurrentCall concurrentCall;

    @BeforeEach
    void setup() {
        getter = Main.ioc();
        concurrentCall = new ConcurrentCall(getter);
    }

    @Test
    public void should_invoke() throws ExecutionException, InterruptedException {
        List result = concurrentCall.invoke(
                ()-> 5000,
                () -> "2000"
        );
        assertEquals(result.get(1), "2000");
        assertEquals(result.get(0), 5000);
    }
}