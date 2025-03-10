package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {
    private static class Bucket {
        private final int capacity;
        private final int refillRate;
        private final int maxCredits;
        private int tokens;
        private int credits;
        private long lastRefillTimestamp;

        public Bucket(int capacity, int refillRate, int maxCredits) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.maxCredits = maxCredits;
            this.tokens = capacity;
            this.credits = 0;
            this.lastRefillTimestamp = System.nanoTime();
        }

        synchronized boolean allowRequest() {
            refillTokens();

            if (tokens > 0) {
                tokens--;
                return true;
            } else if (credits > 0) {
                credits--;
                return true;
            } else {
                return false;
            }
        }

        private void refillTokens() {
            long now = System.nanoTime();
            long elapsedTime = now - lastRefillTimestamp;
            int newTokens = (int) (elapsedTime / 1_000_000_000L * refillRate);

            if (newTokens > 0) {
                int unusedTokens = Math.max(0, capacity - tokens);
                int availableForCredit = unusedTokens > newTokens ? newTokens : unusedTokens;

                tokens = Math.min(capacity, tokens + newTokens);
                credits = Math.min(maxCredits, credits + availableForCredit);

                lastRefillTimestamp = now;
            }
        }
    }

    private final ConcurrentHashMap<String, Bucket> customerBuckets = new ConcurrentHashMap<>();
    private final int capacity;
    private final int refillRate;
    private final int maxCredits;

    public TokenBucketRateLimiter(int capacity, int refillRate, int maxCredits) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.maxCredits = maxCredits;
    }

    public boolean allowRequest(String customerId) {
        customerBuckets.putIfAbsent(customerId, new Bucket(capacity, refillRate, maxCredits));
        return customerBuckets.get(customerId).allowRequest();
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(10, 2, 5);
        String customerId = "customer_1";

        // Simulating requests
        for (int i = 0; i < 15; i++) {
            boolean allowed = rateLimiter.allowRequest(customerId);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rate Limited"));
            Thread.sleep(300);
        }
    }
}

