package basic.knowledge.henry.ThreadRelevant._20cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RunnableFirstlt {
    //实例化CyclicBarrier，并指定回调函数
    static CyclicBarrier c = new CyclicBarrier(2,new TestRunable());
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("1");
            }
        }).start();
        try {
            Thread.sleep(200);
            c.await();
            System.out.println("2");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    static class TestRunable implements Runnable{
        @Override
        public void run() {
            System.out.println("3");
        }
    }

}
