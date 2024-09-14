package basic.knowledge.henry.ThreadRelevant._26threadSafe.countThreadSafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadSafeCounterTest {

    private ThreadSafeCounter counter;

    @BeforeEach
    public void setUp() {
        counter = new ThreadSafeCounter();
    }



    @Test
    public void testThreadSafety() throws InterruptedException {
        int numThreads = 100;
        int numIncrementsPerThread = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        Runnable task = () -> {
            try {
                for (int i = 0; i < numIncrementsPerThread; i++) {
                    counter.increment("task1");
                    counter.decrement("task2");
                }
            } finally {
                latch.countDown();
            }
        };

        // Execute tasks concurrently
        for (int i = 0; i < numThreads; i++) {
            executor.execute(task);
        }

        // Wait for all threads to complete
        latch.await();

        // Validate final counts
        assertEquals(numThreads * numIncrementsPerThread, counter.getCount("task1"));
        assertEquals(-numThreads * numIncrementsPerThread, counter.getCount("task2"));

        // Shutdown the executor
        executor.shutdown();
    }
}
