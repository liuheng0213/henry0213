package basic.knowledge.henry.ThreadRelevant._15concurrentAPI.atomicReference;

import java.util.concurrent.atomic.AtomicReference;

public class TestCorrectByReference {
    private static AtomicReference<Data> data = new AtomicReference<>();

    public static void setData(int a, int b) {
        data.compareAndSet(null, new Data(a, b));
    }

    private static class Data {
        private int a;
        private int b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) {
            int a = i;
            int b = i;
            //writer
            Thread writerThread = new Thread(() -> {setData(a, b);});
            //reader
            Thread readerThread = new Thread(() -> {
                while (data.get() == null) {}
                int x = data.get().getA();
                int y = data.get().getB();
                if (x != y) {
                    System.out.printf("a = %s, b = %s%n", x, y);
                }
            });

            writerThread.start();
            readerThread.start();
            writerThread.join();
            readerThread.join();
        }
        System.out.println("finished");
    }
}


