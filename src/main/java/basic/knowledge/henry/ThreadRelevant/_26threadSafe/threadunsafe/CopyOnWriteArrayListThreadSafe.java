package basic.knowledge.henry.ThreadRelevant._26threadSafe.threadunsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList<>();

        // Creating two threads that modify the ArrayList concurrently
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
                System.out.println("name1: "+ i + " "+ Thread.currentThread().getName());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
                System.out.println("name2: "+ i + " "+Thread.currentThread().getName());
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish or use sleep is also OK
        // it means main thread will be blocked till both of two threads finish
        // basically  it is main thread to call join function for thread1 and thread2
        thread1.join();
        thread2.join();

        // Print the size of the list,equals 2000 if it is thread safe
        System.out.println("List size: " + list.size());
    }
}
