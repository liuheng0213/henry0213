package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SlidingWindowRateLimiter {
    private final int maxRequests;
    private final long windowDurationInMillis;
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Long>> requestLog = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long windowDurationInMillis) {
        this.maxRequests = maxRequests;
        this.windowDurationInMillis = windowDurationInMillis;
    }

    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        requestLog.putIfAbsent(clientId, new ConcurrentLinkedQueue<>());

        ConcurrentLinkedQueue<Long> requests = requestLog.get(clientId);
        synchronized (requests) {
            while (!requests.isEmpty() && currentTime - requests.peek() >= windowDurationInMillis) {
                requests.poll();
            }

            if (requests.size() < maxRequests) {
                requests.add(currentTime);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(50, 1000); // 5 requests per second
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
