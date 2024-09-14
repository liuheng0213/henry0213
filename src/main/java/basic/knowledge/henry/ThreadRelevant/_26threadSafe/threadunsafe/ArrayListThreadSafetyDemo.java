package basic.knowledge.henry.ThreadRelevant._26threadSafe.threadunsafe;

import java.util.ArrayList;
import java.util.List;

public class ArrayListThreadSafetyDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();

        // Creating two threads that modify the ArrayList concurrently
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish，
        //这是主线程对两个新建线程调用join
        thread1.join();
        thread2.join();

        // Print the size of the list, less than 2000
        System.out.println("List size: " + list.size());
    }
}

