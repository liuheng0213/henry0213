package basic.knowledge.henry.ThreadRelevant._00synchronized;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
//        test1();

        test2();
    }

    public static void test1() {
        CountDownLatch latch = new CountDownLatch(2);
        User user = new User(); // 创建user对象
        User user2 = new User(); // 创建user2对象
        new Thread(() -> {
            user.getUp();
            latch.countDown();
        }).start();
//        try {
//            TimeUnit.SECONDS.sleep(1L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(() -> {
            user2.eatBreakfast();
            latch.countDown();
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 任何进入User运行getup的线程会sleep，而且无法block进入eatbreakfast的线程
     */
    public static void test2(){

        CountDownLatch latch = new CountDownLatch(2);
        User user = new User(); // 创建user对象

        new Thread(() -> {
            user.getUp();
            latch.countDown();
        }).start();
//        try {
//            TimeUnit.SECONDS.sleep(1L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(() -> {
            user.eatBreakfast();
            latch.countDown();
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
