package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这是一个错误的示范：
 * 每一次都更新lastRefillTime 导致根本加不了token 因为elapsedTime 永远都是800 左右
 */
public class TokenBucketAPILimiterERROR {
    private final int capacity;
    private final long refillRate;// refillrate milli second for one token
    private final ConcurrentHashMap<String, Integer> userTokens = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> lastRefillTime = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();

    public TokenBucketAPILimiterERROR(int capacity, long refillRateMillis) {
        this.capacity = capacity;
        this.refillRate = refillRateMillis;
    }

    public boolean allowRequest(String userId) {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            lastRefillTime.putIfAbsent(userId, now);
            userTokens.putIfAbsent(userId, capacity);
            int latestTokens = calculate(now,userId);
            userTokens.put(userId, latestTokens);
            lastRefillTime.put(userId, now);

            if (userTokens.get(userId) > 0) {
                userTokens.put(userId, userTokens.get(userId) - 1);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private int calculate(long now, String userId) {
        long elapsedTime = now - lastRefillTime.get(userId);
        System.out.println("elapsed time " + elapsedTime);
        int tokensToAdd = (int) (elapsedTime / refillRate);
        System.out.println("added tokens " + tokensToAdd);
        int newTokenCount = Math.min(capacity, userTokens.get(userId) + tokensToAdd);
//        System.out.println("latest  tokens " + newTokenCount);
        return newTokenCount;
    }

    public static void main(String[] args) {
        TokenBucketAPILimiterERROR tokenBucketAPILimiterERROR = new TokenBucketAPILimiterERROR(5, 1000);
        int numberOfThreads = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            executorService.submit(() -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    boolean allowed = tokenBucketAPILimiterERROR.allowRequest(String.valueOf(threadId));
                    System.out.println("Thread " + threadId + " - Request " + j + ": " + allowed +" at " + System.currentTimeMillis());
                }
            });
        }

        executorService.shutdown();
    }
}
