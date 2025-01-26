package basic.knowledge.henry.ThreadRelevant._24InstanceLockAndClassLock.instanceLock;



//https://blog.csdn.net/zcg_741454897/article/details/108540717
public class ThreadSafe implements Runnable{
     Integer ticket = 100;

    @Override
    public void run() {
        sellTicket(); // Set the thread task to call the sellTicket method
    }

    public void sellTicket() { // Removed synchronized to make it thread-unsafe
        synchronized (ticket) {
            while (ticket > 0) {
                try {
                    Thread.sleep(200); // Simulate delay to increase the chance of a race condition
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    ticket--; // Sell a ticket if available
                    System.out.println(Thread.currentThread().getName() + " is selling ticket #" + ticket);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafe ts = new ThreadSafe(); // Create a shared object
        for (int i = 0; i < 10; i++) { // Create 10 threads that access the same object
            new Thread(ts).start();
        }
    }
}

