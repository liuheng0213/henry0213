package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._11SnakeGame.SnakeGame;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertTrue;

public class TestSnakeGame {
    @Test
    public void test2() throws InterruptedException {
        final SnakeGame game1 = new SnakeGame(5, 5); // shared or not, depending on design
        final SnakeGame game2 = new SnakeGame(5, 5); // shared or not, depending on design
        final String direction = "R"; // the direction you want to pass

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                game1.move("D"); // using the final variable
            }
        });



        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                game2.move("D"); // using the final variable
            }
        });
        t1.start();
        t2.start();
        t1.join();
    }
    @Test
    public void testMultipleThreadsPlayingOneGameOnly() throws InterruptedException, ExecutionException {
        int numThreads = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<String>> futures = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            futures.add(executor.submit(() -> {
                SnakeGame snake = new SnakeGame(5, 5);
                latch.await(); // ensure all threads start together
                String[] moves = {"R", "D", "L", "U", "R", "D", "L", "U", "R"};
                try {
                    for (String dir : moves) {
                        snake.move(dir);
                        Thread.sleep(10); // simulate human-like delay
                    }
                    return "Thread " + threadId + " finished normally.";
                } catch (Exception e) {
                    return "Thread " + threadId + " was interrupted.";

                }
            }));
        }

        latch.countDown(); // release all threads at the same time




        // Wait for threads to complete and collect results
        int playingThreads = 0;
        for (Future<String> future : futures) {
            String result = future.get();
            System.out.println(result);
            if (result.contains("finished normally")) {
                playingThreads++;
            }
        }

        executor.shutdown();

        // At most one thread should be able to "play" and finish
        assertTrue("Only one thread should be able to finish playing the game", playingThreads <= 1);
    }


    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch cd =new CountDownLatch(1);
        List<Future<String>> futureList = new ArrayList<>();//future 运行的结果，futuretask 是task
        for(int i =0;i< 5;i++){
            final int threadId = i;
            futureList.add(executorService.submit(() -> {
                SnakeGame snake = new SnakeGame(5,5);
                try {
                    cd.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String[] moves = {"R", "D", "L", "U", "R", "D", "L", "U", "R"};
                try {
                    for (String dir : moves) {
                        snake.move(dir);
                        Thread.sleep(10); // simulate human-like delay
                    }
                    return "Thread " + threadId + " finished normally.";
                } catch (Exception e) {
                    return "Thread " + threadId + " was interrupted.";
                }
            }));
        }

        cd.countDown();

    }
}
