package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixWindowRateLimiterWithCreditBest {
    private final int maxRequests;
    private final long windowDurationInMillis;
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> userToCredit = new ConcurrentHashMap<>();
    private final int maxCredit = 5;

    public FixWindowRateLimiterWithCreditBest(int maxRequests, long windowDurationInMillis) {
        this.maxRequests = maxRequests;
        this.windowDurationInMillis = windowDurationInMillis;
    }

    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        Window window;

        //先判断时间是不是符合， 不符合，重启一个窗口
        //符合， 判断数量
        synchronized (windows) {
            System.out.println("now " + currentTime);

            // Atomically check and update the `windows` map
            if (!windows.containsKey(clientId) || currentTime - windows.get(clientId).startTime >= windowDurationInMillis) {

                System.out.println("inside1");
                if(windows.containsKey(clientId)){
                    System.out.println("start  " + windows.get(clientId).startTime);
                    int used = windows.get(clientId).requestCount.get();
                    Integer credits = userToCredit.getOrDefault(clientId, 0);
                    credits = Math.min(maxCredit,credits+ maxRequests - used);
                    System.out.println("pre credits " + credits);
                    userToCredit.put(clientId,credits);
                }else{
                    userToCredit.put(clientId,0);
                    System.out.println("====");
                }

                window = new Window(currentTime);
                windows.put(clientId, window);
            } else {
                window = windows.get(clientId);
                System.out.println("inside2");
            }
        }
        int credits = userToCredit.get(clientId);
        System.out.println("current credits " + credits);
        if (window.requestCount.get() < maxRequests) {
            window.requestCount.incrementAndGet();
            return true;
        }else if(credits > 0){
            credits--;
            System.out.println("from credit ==============");
            userToCredit.put(clientId,credits);
            return true;
        }
        return false;

    }

    private static class Window {
        long startTime;
        AtomicInteger requestCount = new AtomicInteger(0);

        Window(long startTime) {
            this.startTime = startTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(159000/1000);
//        System.out.println(159001/1000);
//        System.out.println(159002/1000);
        FixWindowRateLimiterWithCreditBest rateLimiter = new FixWindowRateLimiterWithCreditBest(5, 1000); // 5 requests per second

        boolean allowed1 = rateLimiter.allowRequest("henry");
        System.out.println(allowed1);
        Thread.sleep(400);
        boolean allowed2 = rateLimiter.allowRequest("henry");
        System.out.println(allowed2);
        Thread.sleep(400);
        boolean allowed3 =rateLimiter.allowRequest("henry");
        Thread.sleep(400);
        System.out.println(allowed3);
        boolean allowed4 =rateLimiter.allowRequest("henry");
        System.out.println(allowed4);

        boolean allowed5 =rateLimiter.allowRequest("henry");
        System.out.println(allowed5);

        boolean allowed6 =rateLimiter.allowRequest("henry");
        System.out.println(allowed6);

        boolean allowed7 =rateLimiter.allowRequest("henry");
        System.out.println(allowed7);

        boolean allowed8 =rateLimiter.allowRequest("henry");
        System.out.println(allowed8);

        boolean allowed9 =rateLimiter.allowRequest("henry");
        System.out.println(allowed9);
//        int numberOfThreads = 10;

//        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            final int threadId = i;
//            executorService.submit(() -> {
//                for (int j = 0; j < 50; j++) {
//                    try {
//                        Random random = new Random();
//                       int r =  random.nextInt(500) + 200;
//                        System.out.println("random " + r);
//                        Thread.sleep(r);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    boolean allowed = rateLimiter.allowRequest(String.valueOf(threadId));
//                    System.out.println("Thread " + threadId + " - Request " + j + ": " + allowed +" at " + System.currentTimeMillis());
//                }
//            });
//        }
//
//        executorService.shutdown();
    }
}
