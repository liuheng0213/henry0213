package basic.knowledge.henry.ThreadRelevant._26threadSafe.countThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {
    private final ConcurrentHashMap<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    // Increment the counter for a specific key
    public void increment(String key) {
        counters.computeIfAbsent(key, k -> new AtomicInteger(0)).incrementAndGet();

    }

    // Decrement the counter for a specific key
    public void decrement(String key) {
        counters.computeIfAbsent(key, k -> new AtomicInteger(0)).decrementAndGet();

    }

    // Get the current value of the counter for a specific key
    public int getCount(String key) {
        return counters.getOrDefault(key, new AtomicInteger(0)).get();
    }
}

