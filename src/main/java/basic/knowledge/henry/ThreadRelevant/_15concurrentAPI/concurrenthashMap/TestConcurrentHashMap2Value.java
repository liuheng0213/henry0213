package basic.knowledge.henry.ThreadRelevant._15concurrentAPI.concurrenthashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//https://www.cnblogs.com/zwh0910/p/14440248.html
//following is correct , atomic has to be used
public class TestConcurrentHashMap2Value {
    public static void main(String[] args) {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
//        final Map<String, Integer> count = new HashMap<>();
//        final Map<String, Integer> count = new Hashtable<>();
        count.put("count", new AtomicInteger(0));
        Runnable task = new Runnable() {
            @Override
            public void run() {
                // 复合操作, 并不安全
                //should use synchronized (count){
                AtomicInteger value;
                for (int i = 0; i < 2000; i++) {
                    value = count.get("count");
                    count.get("count").incrementAndGet();
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
        try {
            Thread.sleep(1000l);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
