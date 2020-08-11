package top.bujiaban.openjava.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.stream.Collectors.toList;

public class ConcurrentCall {

    private FutureGetter futureGetter;

    public ConcurrentCall(FutureGetter futureGetter) {
        this.futureGetter = futureGetter;
    }

    public List invoke(Supplier... suppliers) throws ExecutionException, InterruptedException {
        List<CompletableFuture> allFutures = Arrays.stream(suppliers)
                .map(this.futureGetter::supplyAsync)
                .collect(toList());

        CompletableFuture<Void> allDoneFuture = allOf(allFutures.toArray(new CompletableFuture[allFutures.size()]));
        return allDoneFuture.thenApply(f -> allFutures.stream().map(CompletableFuture::join).collect(toList()))
                .get();
    }
}
