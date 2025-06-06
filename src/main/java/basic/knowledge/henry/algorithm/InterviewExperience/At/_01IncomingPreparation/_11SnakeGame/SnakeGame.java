package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._11SnakeGame;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

//this is an threadSafe modification. with higher performance,fine-grained locking.
//or can use synthronized to lock the isGameOver or the whole method

/**
 * When you have multiple shared mutable variables that must be updated together atomically.
 * <p>
 * When operations are complex and can’t be handled by atomic variables alone.
 * <p>
 * When you need to protect compound actions (read-modify-write sequences) on multiple fields.
 */
public class SnakeGame{
    private final LinkedList<int[]> body;
    private final int m;
    private final int n;
    private int steps;

    private static final AtomicBoolean isGameOver = new AtomicBoolean(false);
    private static final ReentrantLock gameLock = new ReentrantLock();

    public SnakeGame(int m, int n) {
        this.body = new LinkedList<>();
        this.body.add(new int[]{0, 0});
        this.body.add(new int[]{0, 1});
        this.body.add(new int[]{0, 2});
        this.body.add(new int[]{0, 3});
        this.m = m;
        this.n = n;
        this.steps = 0;
    }

    public void move(String dir) {
        // Don't start if game over
        if (isGameOver.get()) {
            throw new RuntimeException("game is not over");
        }

        // tryLock() enforces exclusive access — it prevents multiple threads from playing concurrently.
        if (!gameLock.tryLock()) {
            throw new RuntimeException("game is not over");
        }
        try{
            int[] head = body.peekFirst();
            int i = head[0];
            int j = head[1];

            switch (dir) {
                case "D": i++; break;
                case "U": i--; break;
                case "L": j--; break;
                case "R": j++; break;
            }

            // Wrap around
            if (i < 0) i = m - 1;
            else if (i >= m) i = 0;
            if (j < 0) j = n - 1;
            else if (j >= n) j = 0;

            head = new int[]{i, j};
            body.addFirst(head);
            steps++;
            if (steps % 5 != 0) {
                body.pollLast(); // No growth
            }

            // Collision check
            for (int k = 1; k < body.size(); k++) {
                int[] part = body.get(k);
                if (part[0] == head[0] && part[1] == head[1]) {
                    isGameOver.compareAndSet(false,true);
                    gameLock.unlock(); // Release the lock when game is over
                    return;
                }
            }
        }catch(Exception e) {
            gameLock.unlock();
            throw new RuntimeException("game is not over");
        }

    }



}