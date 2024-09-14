package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode2851_2 {

    public static void main(String[] args) {
        Leetcode2851_2 obj = new Leetcode2851_2();
        int res = obj.numberOfWays("ababab", "ababab", 1);

        System.out.println(res);
    }
    private static final long M = 1_000_000_007L;

    public int numberOfWays(String s, String t, long k) {
        String ss = s + s;
        ss = ss.substring(0, ss.length() - 1);  // equivalent to ss.pop_back() in C++

        int p = strStr(ss, t);

        int n = s.length();
        long[] T = new long[]{n - p - 1, n - p, p, p - 1};
        long[] Tk = quickMul(T, k);

        if (s.equals(t))
            return (int) Tk[3];  // Tk * (0, 1)'
        else
            return (int) Tk[2];  // Tk * (1, 0)'
    }

    private long[] multiply(long[] mat1, long[] mat2) {
        long a1 = mat1[0], b1 = mat1[1], c1 = mat1[2], d1 = mat1[3];
        long a2 = mat2[0], b2 = mat2[1], c2 = mat2[2], d2 = mat2[3];
        return new long[]{
                (a1 * a2 + b1 * c2) % M,
                (a1 * b2 + b1 * d2) % M,
                (c1 * a2 + d1 * c2) % M,
                (c1 * b2 + d1 * d2) % M
        };
    }

    private long[] quickMul(long[] mat, long N) {
        if (N == 0) {
            return new long[]{1, 0, 0, 1};
        }
        long[] mat2 = quickMul(mat, N / 2);
        if (N % 2 == 0)
            return multiply(mat2, mat2);
        else
            return multiply(multiply(mat2, mat2), mat);
    }

    public int strStr(String haystack, String needle) {
        int count = 0;

        int n = haystack.length();
        int m = needle.length();

        int[] suf = preprocess(needle);

        int[] dp = new int[n];
        dp[0] = (haystack.charAt(0) == needle.charAt(0)) ? 1 : 0;
        if (m == 1 && dp[0] == 1)
            count++;

        for (int i = 1; i < n; i++) {
            int j = dp[i - 1];
            while (j > 0 && haystack.charAt(i) != needle.charAt(j))
                j = suf[j - 1];
            dp[i] = j + (haystack.charAt(i) == needle.charAt(j) ? 1 : 0);
            if (dp[i] == needle.length())
                count++;
        }
        return count;
    }

    private int[] preprocess(String s) {
        int n = s.length();
        //dp[i] means s ends with index i has the longest common sub string of number dp[i]
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int j = dp[i - 1];
            while (j >= 1 && s.charAt(j) != s.charAt(i)) {
                j = dp[j - 1];
            }
            dp[i] = j + (s.charAt(j) == s.charAt(i) ? 1 : 0);
        }
        return dp;
    }
}
