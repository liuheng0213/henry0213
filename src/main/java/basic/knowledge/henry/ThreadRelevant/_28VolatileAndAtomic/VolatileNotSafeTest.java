package basic.knowledge.henry.ThreadRelevant._28VolatileAndAtomic;

/**
 * 此例主线城和新建线程都要加锁
 */
public class VolatileNotSafeTest {
    private static int count = 0;
    private static final int times = Integer.MAX_VALUE;
    private static final String LOCK = "lock";
    public static void main(String[] args) {
        long curTime = System.nanoTime();
        Thread decThread = new DecThread();
        decThread.start();
        // 使用run()来运行结果为0,原因是单线程执行不会有线程安全问题
        // new DecThread().run();
        synchronized (LOCK){
            System.out.println("Start thread: "+ Thread.currentThread() + " i++");
            for(int i = 0; i < times; i++) {
                count++;
            }
            System.out.println("End thread: "+ Thread.currentThread() + " i--");
        }

        // 等待decThread结束
        try {
            decThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        while(decThread.isAlive());
        long duration = System.nanoTime() - curTime;
        System.out.println("Result: "+ count);
        System.out.format("Duration: %.2fs\n", duration / 1.0e9);
    }
    private static class DecThread extends Thread{
        @Override
        public void run() {
//            synchronized (LOCK){
                System.out.println("Start thread: "+ Thread.currentThread() + " i--");
                for(int i = 0; i < times; i++) {
                    count--;
                }
                System.out.println("End thread: "+ Thread.currentThread() + " i--");
//            }

        }
    }
}
