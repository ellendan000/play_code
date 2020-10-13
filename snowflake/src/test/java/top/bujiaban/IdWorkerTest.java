package top.bujiaban;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IdWorkerTest {

    @Test
    void nextId() {
        int num = 2000000;

        IdWorker idWorker = new IdWorker(17L, 27L, 0);
        Set<String> ids = IntStream.range(0, num)
                .parallel()
                .mapToObj(value -> {
                    String id = Long.toBinaryString(idWorker.nextId(new Date()));
//                    System.out.println(id);
                    return id;
                })
                .collect(toSet());
        assertEquals(num, ids.size());
    }

    @Test
    void test(){
        System.out.println(Long.toBinaryString(1000L * 60L * 60L * 24L * 25L).length());
    }
}