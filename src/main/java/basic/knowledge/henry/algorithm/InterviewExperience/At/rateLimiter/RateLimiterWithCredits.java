package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 *  //For each bucket of time,
 *  any capacity available below the limit is converted into credits for that customer
 *     //There is some maximum number of credits that a customer can accumulate
 *     //When a customer exceeds their normal request limit for a window, the credit count starts to decrease.
 *     //When there are 0 credits, the customer is rate limited
 *
 *
 */
public class RateLimiterWithCredits {
    public static void main(String[] args) {
        RateLimiterWithCredits tokenBucketAPILimiter = new RateLimiterWithCredits(5, 1000,800);
        for (int j = 0; j < 50; j++) {
            try {
                Thread.sleep(550);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean allowed = tokenBucketAPILimiter.rateLimit(1234);
            System.out.println("Thread " + 1234 + " - Request " + j + ": " + allowed +" at " + System.currentTimeMillis());
        }


    }


    private int maxRequest;
    private int timeWindow;
    private Map<Integer, Integer> requestCounts;
    private Map<Integer, Integer> credits;
    private ScheduledExecutorService scheduler;
    private int MAX_CREDIT = 5;

    public RateLimiterWithCredits(int maxRequest, int timeWindow,int period) {
        this.maxRequest = maxRequest;
        this.timeWindow = timeWindow;
        this.requestCounts = new HashMap<>();
        this.credits = new HashMap<>();
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->refill(), timeWindow, period, TimeUnit.MILLISECONDS);

    }

    private void refill() {
        for(Map.Entry<Integer, Integer> requestCount:requestCounts.entrySet()) {
            int customerId = requestCount.getKey();
            int count = requestCount.getValue();
            int credit = credits.getOrDefault(customerId, 0);

            credit = Math.min(MAX_CREDIT, credit + (maxRequest-count));
            System.out.println("count: " + count);
            System.out.println("credit: " + credit);
            credits.put(customerId, credit);
        }
        // which means it can start sending the request
        requestCounts.clear();// move from credit to requestCounts, so requestCouts should be cleared
    }

    public boolean rateLimit(int customerId) {
        int requestCount = requestCounts.getOrDefault(customerId, 0);
        int credit = credits.getOrDefault(customerId, 0);

        if(requestCount<maxRequest) {
            requestCount++;
            requestCounts.put(customerId, requestCount);
            System.out.println("from requestCount");
            return true;
        } else if(credit>0) {
            credit--;
            System.out.println("from credit");
            credits.put(customerId, credit);
            return true;
        } else {
            return false;
        }
    }
}
