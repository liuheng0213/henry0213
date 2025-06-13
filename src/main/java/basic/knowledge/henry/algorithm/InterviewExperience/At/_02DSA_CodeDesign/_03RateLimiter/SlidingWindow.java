package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindow {
    private final int maxRequest;
    private final int timeWindowInSecond;
    private final Map<Integer, LinkedList<Long>> requestLogs;

    public SlidingWindow(int maxRequest, int timeWindowInSecond) {
        this.maxRequest = maxRequest;
        this.timeWindowInSecond = timeWindowInSecond;
        this.requestLogs = new ConcurrentHashMap<>();
    }

    // Lock striping array to reduce contention and avoid per-customer lock creation
    private final Object[] locks = new Object[1024];
    {
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new Object();
        }
    }

    private Object getLock(int customerId) {
        return locks[customerId % locks.length];
    }

    public boolean sendRequest(int customerId) {
        long current = System.currentTimeMillis();

        // Lock on a per-customer basis
        synchronized (getLock(customerId)) {
            LinkedList<Long> requestLog = requestLogs.computeIfAbsent(customerId, k -> new LinkedList<>());
            long windowStartTime = current - timeWindowInSecond * 1000;

            while (!requestLog.isEmpty() && requestLog.peek() < windowStartTime) {
                requestLog.poll();
            }

            if (requestLog.size() < maxRequest) {
                requestLog.offer(current);
                return true;
            } else {
                return false;
            }
        }
    }
}
