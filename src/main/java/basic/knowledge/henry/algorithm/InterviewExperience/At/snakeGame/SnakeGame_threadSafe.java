package basic.knowledge.henry.algorithm.InterviewExperience.At.snakeGame;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class SnakeGame_threadSafe {
    private final int width;
    private final int height;

    private final ConcurrentLinkedDeque<int[]> snake;
    private final ConcurrentHashMap<String, Boolean> bodyPositions; // Thread-safe set
    private final AtomicInteger steps;

    public SnakeGame_threadSafe(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new ConcurrentLinkedDeque<>();
        this.snake.addFirst(new int[]{0, 0});
        this.bodyPositions = new ConcurrentHashMap<>();
        this.bodyPositions.put("0,0", true); // Initial position
        this.steps = new AtomicInteger(0);
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 4));
        System.out.println((20 >>> 4));
        System.out.println((20 / (int) (Math.pow(2, 4))));
        System.out.println((20 >>> 4) & 1);
        SnakeGame_threadSafe solution = new SnakeGame_threadSafe(3, 2);
        int r = solution.move("R");
        System.out.println(r);
        int d = solution.move("D");
        System.out.println(d);
        int r1 = solution.move("R");
        System.out.println(r1);
        int u = solution.move("U");
        System.out.println(u);
        int l = solution.move("L");
        System.out.println(l);
        int l1 = solution.move("R");
        System.out.println(l1);

        int u1 = solution.move("U");
        System.out.println(u1);
        System.out.println();
    }

    private int move(String direction) {
        int currentSteps = steps.incrementAndGet();
        int[] head = snake.peekFirst();
        int i = head[0];
        int j = head[1];

        switch (direction) {
            case "U":
                i--;
                break;
            case "R":
                j++;
                break;
            case "D":
                i++;
                break;
            case "L":
                j--;
                break;
        }

        // Check if the new head position is out of bounds
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return -1;
        }

        // Check for collision with the snake's body
        String newHeadPosition = i + "," + j;
        if (bodyPositions.containsKey(newHeadPosition)) {
            return -1;
        }

        // Add the new head position to the snake and bodyPositions
        int[] newHead = new int[]{i, j};
        snake.addFirst(newHead);
        bodyPositions.put(newHeadPosition, true);

        // Remove the tail if not growing (every 5 steps)
        if (currentSteps % 5 != 0) {
            int[] tail = snake.pollLast();
            bodyPositions.remove(tail[0] + "," + tail[1]);
        }

        return snake.size();
    }
}
