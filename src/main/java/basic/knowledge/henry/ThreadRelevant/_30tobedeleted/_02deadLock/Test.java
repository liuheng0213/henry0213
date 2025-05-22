package basic.knowledge.henry.ThreadRelevant._30tobedeleted._02deadLock;

public class Test {
    public static void main(String[] args) {
        new Test().testDeadLock();
    }
    final Object lock1 = new Object();
    final Object lock2 = new Object();
    private void testDeadLock() {
        Thread threada = new Thread(()->{
//            while(true){
                synchronized (lock1){
                    System.out.println("threada is holding lock1");
                    synchronized (lock2){
                        System.out.println("threada is holding lock2");
                    }
                }
//            }

        });


        Thread threadb = new Thread(()->{
//            while(true){
                synchronized (lock2){
                    System.out.println("threadb is holding lock2");
                    synchronized (lock1){
                        System.out.println("threadb is holding lock1");
                    }
                }
//            }
        });


        threadb.start();
        threada.start();
    }
}
