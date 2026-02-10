package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter;

public class TokenBucketRateLimiter {
    public static void main(String[] args) {
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(5);
        boolean b = tokenBucketRateLimiter.canSendRequest(1);
        boolean b1 =tokenBucketRateLimiter.canSendRequest(1);
        boolean b2 =tokenBucketRateLimiter.canSendRequest(1);
        boolean b3 =tokenBucketRateLimiter.canSendRequest(1);
        boolean b4 =tokenBucketRateLimiter.canSendRequest(1);
        boolean b5 =tokenBucketRateLimiter.canSendRequest(1);
        boolean b6 =tokenBucketRateLimiter.canSendRequest(2);
        System.out.println();
    }
    private final int capacity;          // max burst
    private double tokens;               // allow fractional refill
    private long lastRefillMs;           // last refill timestamp

    public TokenBucketRateLimiter(int capacity) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.lastRefillMs = System.currentTimeMillis();
    }

    // bucketTime: ms per token (e.g., 1000 => 1 req/sec), for one user
    // bucketTime 每秒or每毫秒 生成的令牌数
    public synchronized boolean canSendRequest(int bucketTime) {
        long now = System.currentTimeMillis();

        // Refill tokens based on time passed
        long elapsed = now - lastRefillMs;
        if (elapsed > 0) {
            double refill = (double) elapsed / bucketTime; // tokens gained
            tokens = Math.min(capacity, tokens + refill);
            lastRefillMs = now;
        }

        // Consume one token if available
        if (tokens >= 1.0) {
            tokens -= 1.0;
            return true;
        }
        return false;
    }
}
