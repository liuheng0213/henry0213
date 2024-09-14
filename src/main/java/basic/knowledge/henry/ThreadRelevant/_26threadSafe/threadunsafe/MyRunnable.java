package basic.knowledge.henry.ThreadRelevant._26threadSafe.threadunsafe;

import java.util.List;

public class MyRunnable implements Runnable{
    List<Integer> list;

    public MyRunnable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        }
    }
}
