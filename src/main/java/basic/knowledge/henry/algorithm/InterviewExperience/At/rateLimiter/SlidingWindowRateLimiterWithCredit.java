package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

public class SlidingWindowRateLimiterWithCredit {
    private static class CustomerWindow {
        private final int maxRequests;
        private final long windowSizeMillis;
        private final int maxCredits;
        private int credits;
        private final Queue<Long> requestTimestamps;

        public CustomerWindow(int maxRequests, long windowSizeMillis) {
            this.maxRequests = maxRequests;
            this.windowSizeMillis = windowSizeMillis;
            this.maxCredits = 3;
            this.credits = 0;
            this.requestTimestamps = new ConcurrentLinkedQueue<>();
        }

        synchronized boolean allowRequest() {
            long now = System.currentTimeMillis();
            removeExpiredRequests(now);// not correct

            if (requestTimestamps.size() < maxRequests) {
                requestTimestamps.add(now);
                System.out.println("from request");
                return true;
            } else if (credits > 0) {
                System.out.println("from credit");
                credits--;
                return true;
            } else {
                return false;
            }
        }

        private void removeExpiredRequests(long now) {
            while (!requestTimestamps.isEmpty() && requestTimestamps.peek() < now - windowSizeMillis) {
                requestTimestamps.poll();
            }
            accumulateCredits();
        }

        private void accumulateCredits() {
            int unusedRequests = maxRequests - requestTimestamps.size();
            if (unusedRequests > 0) {
                credits = Math.min(maxCredits, credits + unusedRequests);
            }
        }
    }

    private final ConcurrentHashMap<String, CustomerWindow> customerWindows = new ConcurrentHashMap<>();
    private final int maxRequests;
    private final long windowSizeMillis;
    private final int maxCredits;

    public SlidingWindowRateLimiterWithCredit(int maxRequests, long windowSizeMillis, int maxCredits) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
        this.maxCredits = maxCredits;
    }

    public boolean allowRequest(String customerId) {
        customerWindows.putIfAbsent(customerId, new CustomerWindow(maxRequests, windowSizeMillis));
        return customerWindows.get(customerId).allowRequest();
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiterWithCredit rateLimiter = new SlidingWindowRateLimiterWithCredit(5, 10000,3);
        String customerId = "customer_1";

        for (int i = 0; i < 30; i++) {
            boolean allowed = rateLimiter.allowRequest(customerId);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rate Limited"));
            Thread.sleep(500);
        }
    }
}
