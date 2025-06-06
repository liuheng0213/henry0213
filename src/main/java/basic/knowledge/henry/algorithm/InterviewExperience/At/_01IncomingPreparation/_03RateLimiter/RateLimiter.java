package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._03RateLimiter;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * follow up How would you handle this in a multithreaded env
 */
public class RateLimiter {
    private final Map<Integer, Integer> id2Count;
    private final Map<Integer, Integer> id2Credit;
    private final ScheduledExecutorService executor;

    private final int maxCount = 5;
    private final int maxCredit = 6;
    private final int maInterval = 5;

    public RateLimiter() {
        this.id2Count = new ConcurrentHashMap<>();
        this.id2Credit = new ConcurrentHashMap<>();
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(this::clear, maInterval, maInterval, TimeUnit.SECONDS);
    }

    private void clear() {
        for (Integer id : id2Count.keySet()) {
            synchronized (getLockInMap(id)) {
                int count = id2Count.getOrDefault(id, 0);
                int left = maxCount - count;
                id2Count.put(id, 0);

                int credit = id2Credit.getOrDefault(id, 0);
                credit = Math.min(maxCredit, credit + left);
                id2Credit.put(id, credit);
            }
        }
    }

    public boolean sendRequest(int id) {
        synchronized (getLockInMap(id)) {
            int count = id2Count.getOrDefault(id, 0);
            int credit = id2Credit.getOrDefault(id, 0);

            if (count < maxCount) {
                id2Count.put(id, count + 1);
                return true;
            } else if (credit > 0) {
                id2Credit.put(id, credit - 1);
                return true;
            } else {
                return false;
            }
        }
    }

    // Lock striping to prevent excessive object creation and allow concurrent access across users
    private final Object[] locks = new Object[1024];
    {
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new Object();
        }
    }

    private Object getLock(int id) {
        return locks[id % locks.length];
    }

    private final Map<Integer,Object> locksInMap= new ConcurrentHashMap<>();

    private Object getLockInMap(int id){
        return locksInMap.computeIfAbsent(id,k->new Object());
    }



}
