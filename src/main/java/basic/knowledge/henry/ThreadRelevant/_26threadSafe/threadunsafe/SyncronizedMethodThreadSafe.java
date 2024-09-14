package basic.knowledge.henry.ThreadRelevant._26threadSafe.threadunsafe;

import java.util.ArrayList;
import java.util.List;

public class SyncronizedMethodThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        MyRunnable res = new MyRunnable(list);
        // Creating two threads that modify the ArrayList concurrently
        Thread thread1 = new Thread(res);
        Thread thread2 = new Thread(res);

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        /**
         * What Happens Without join()?
         * If you remove join(), the main thread might print the size of the list before thread1 and thread2 have finished adding elements.
         * This could result in a list size that is smaller than expected because the threads are still running and haven't added all their elements.
         */
        thread1.join();
        thread2.join();

        // Print the size of the list, less than 2000
        System.out.println("List size: " + list.size());
    }



}
