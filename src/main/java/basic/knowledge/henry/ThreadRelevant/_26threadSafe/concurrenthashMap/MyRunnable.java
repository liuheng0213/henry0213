package basic.knowledge.henry.ThreadRelevant._26threadSafe.concurrenthashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyRunnable implements Runnable{
    final Map<String, Integer> count;

    public MyRunnable(Map<String, Integer> count){
        this.count = count;
    }
    @Override
    public void run() {
//        synchronized (this){
            int value;
            for (int i = 0; i < 2000; i++) {
                value = count.get("count");
                count.put("count", value + 1);
            }
//        }
    }
}
