package basic.knowledge.henry.ThreadRelevant._26threadSafe.concurrenthashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 主线程加3 个线程
 * 要得到正缺的结果 6000:  1 run里加syncronized,2 用concurrenthashmap
 */

//https://www.cnblogs.com/zwh0910/p/14440248.html
public class TestConcurrentHashMap2Value {
    public static void main(String[] args) {
        Map<String, Integer> count = new ConcurrentHashMap<>();
//        final Map<String, Integer> count = new HashMap<>();
//        final Map<String, Integer> count = new Hashtable<>();
        count.put("count", 0);
        MyRunnable task = new MyRunnable(count) ;

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

//        new Thread(task).start();
//        new Thread(task).start();
//        new Thread(task).start();

        thread1.start();;
        thread2.start();;
        thread3.start();;
//        try {
//            thread1.join();
//            thread2.join();
//            thread3.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }



        try {
            Thread.sleep(1000l);
            System.out.println("count === " + count.get("count"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
