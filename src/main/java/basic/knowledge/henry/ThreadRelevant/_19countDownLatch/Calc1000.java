package basic.knowledge.henry.ThreadRelevant._19countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calc1000 {


    //起码要计算1个亿多线程才有速度优势!!  十个亿能节省一半时间
    //一般不是IO  的操作 完全没必要多线程
    public static void main(String[] args) {
        Calc1000 calc = new Calc1000();
//        long start = System.currentTimeMillis();
//        long result = calc.calculate();
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        System.out.println(result);

        long start2 = System.currentTimeMillis();
        long result2 = calc.calculate();
        long end2 = System.currentTimeMillis();
        System.out.println("total time  " + (end2 - start2));
        System.out.println(result2);
    }

    private long calculateSingleThread() {
        long sum = 0;
        for (int i = 1; i <= 1000000000; i++) {
            sum = sum + i;
        }
        return sum;
    }
    private volatile List<Long> resList = new CopyOnWriteArrayList<>();//ArrayList ?
    private long calculate() {
        try {
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
            CountDownLatch countDownLatch = new CountDownLatch(5);
            fixedThreadPool.execute(new MyThread(1, 200000000, countDownLatch));
            fixedThreadPool.execute(new MyThread(200000001, 400000000, countDownLatch));
            fixedThreadPool.execute(new MyThread(400000001, 600000000, countDownLatch));
            fixedThreadPool.execute(new MyThread(600000001, 800000000, countDownLatch));
            fixedThreadPool.execute(new MyThread(800000001, 1000000000, countDownLatch));
            countDownLatch.await();
            fixedThreadPool.shutdown();// dont forget this to shutdown thread pool usage
            long sum = 0;
            for (long res : resList) {
                sum = sum + res;
            }

            return sum ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private class MyThread implements Runnable {
        private int start;
        private int end;
        private CountDownLatch countDownLatch;
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public MyThread(int start, int end, CountDownLatch countDownLatch) {
            this.start = start;
            this.end = end;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                lock.lock();// actually , for this example , lock is unnecessary, because i am using CopyOnWriteArrayList which is thread safe
                    System.out.println(Thread.currentThread().getName() + ",  begins");
                    resList.add(getPartSum(start, end));
                    System.out.println(Thread.currentThread().getName() + ",  ends");
                lock.unlock();
            }  catch (Exception e){

            } finally {
                countDownLatch.countDown();
            }
        }

        private long getPartSum(int start, int end) {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum = sum + i;
            }
            return sum;
        }
    }
}
