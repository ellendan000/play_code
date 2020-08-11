package top.bujiaban.openjava.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public class FutureGetter {
    private Executor executor;

    public FutureGetter(Executor executor) {
        this.executor = executor;
    }

    public CompletableFuture supplyAsync(Supplier supplier) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }
}
