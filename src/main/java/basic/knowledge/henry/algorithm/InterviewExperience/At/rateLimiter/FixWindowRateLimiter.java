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

        //先判断时间是不是符合， 不符合，重启一个窗口
        //符合， 判断数量
        synchronized (windows) {
            // Atomically check and update the `windows` map
            if (!windows.containsKey(clientId) || currentTime - windows.get(clientId).startTime >= windowDurationInMillis) {
                window = new Window(currentTime);
                windows.put(clientId, window);
            } else {
                window = windows.get(clientId);
            }
        }

        //数量是否符合
        //缺点就在这里， 很可能刚建立0.01秒内 new window时 有5个请求满了，
        // 后面接下来的大部分时间即不能new 一个window， 也不能接受新的请求
        //符合加一个，不符合 返回false
        if (window.requestCount.get() < maxRequests) {
            window.requestCount.incrementAndGet();
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

    public static void main(String[] args) {
        System.out.println(159000/1000);
        System.out.println(159001/1000);
        System.out.println(159002/1000);
        FixWindowRateLimiter rateLimiter = new FixWindowRateLimiter(5, 1000); // 5 requests per second
        int numberOfThreads = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            executorService.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    boolean allowed = rateLimiter.allowRequest(String.valueOf(threadId));
                    System.out.println("Thread " + threadId + " - Request " + j + ": " + allowed +" at " + System.currentTimeMillis());
                }
            });
        }

        executorService.shutdown();
    }
}
