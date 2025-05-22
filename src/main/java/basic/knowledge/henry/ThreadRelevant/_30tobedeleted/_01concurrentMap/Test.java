package basic.knowledge.henry.ThreadRelevant._30tobedeleted._01concurrentMap;

import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        new Test().starRunning();
    }

    ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();

    public void starRunning(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int val = 0;
                for (int i = 0; i < 200; i++) {
                    val = map.getOrDefault("count",0);
                    map.put("count" , val+ 1);
                }

            }
        };
        Thread threada = new Thread(runnable);
        Thread threadb = new Thread(runnable);
        Thread threadc = new Thread(runnable);
        threada.start();
        threadb.start();
        threadc.start();
        try {
            Thread.sleep(1000l);
            System.out.println(map.get("count"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
