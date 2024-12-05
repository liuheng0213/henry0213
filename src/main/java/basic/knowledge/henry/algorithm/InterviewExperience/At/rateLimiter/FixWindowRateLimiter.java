package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FixWindowRateLimiter {
    private final int maxRequests;
    private final long windowDurationInMillis;
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    public FixWindowRateLimiter(int maxRequests, long windowDurationInMillis) {
        this.maxRequests = maxRequests;
        this.windowDurationInMillis = windowDurationInMillis;
    }

    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        Window window;

        synchronized (windows) {
            // Atomically check and update the `windows` map
            if (!windows.containsKey(clientId) || currentTime - windows.get(clientId).startTime >= windowDurationInMillis) {
                window = new Window(currentTime);
                windows.put(clientId, window);
            } else {
                window = windows.get(clientId);
            }
        }

        synchronized (window) {
            if (window.requestCount.get() < maxRequests) {
                window.requestCount.incrementAndGet();
                return true;
            }
            return false;
        }
    }

    private static class Window {
        long startTime;
        AtomicInteger requestCount = new AtomicInteger(0);

        Window(long startTime) {
            this.startTime = startTime;
        }
    }

    public static void main(String[] args) {
        FixWindowRateLimiter rateLimiter = new FixWindowRateLimiter(5, 1000); // 5 requests per second
        int numberOfThreads = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            executorService.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    boolean allowed = rateLimiter.allowRequest("client1");
                    System.out.println("Thread " + threadId + " - Request " + j + ": " + allowed);
                }
            });
        }

        executorService.shutdown();
    }
}
