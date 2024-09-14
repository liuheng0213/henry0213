package basic.knowledge.henry.ThreadRelevant._26threadSafe.threadunsafe;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentHashmapThreadSafe {

    private static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        // 线程1 => t1
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put("thread1_key" + i, "thread1_value" + i);
                }
            }
        }).start();
        // 线程2 => t2
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put("thread2_key" + i, "thread2_value" + i);
                }
            }
        }).start();


        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }

        System.out.println(map.size()); // it should be 2000 totally if it is thread safe

    }

}
