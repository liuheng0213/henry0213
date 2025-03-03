package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode233 {
    public static void main(String[] args) {
        Leetcode233 leetcode233 = new Leetcode233();
        int i = leetcode233.countDigitOne(131);
        //1 10 12,19 , 21,11
        System.out.println(i);
    }

    /**
     * Initialize Variables:
     *
     * c stores the count of 1s.
     * f represents the current digit place (units, tens, hundreds, etc.).
     *
     * Digit-by-Digit Analysis:
     *
     * For each position, split n into three parts:
     *
     * h (higher digits above the current position),
     * cur (current digit at the position),
     * l (lower digits below the current position).
     *
     *
     * Count Contributions:
     *
     * If cur == 1: Add h * f + l + 1 to c.
     * If cur > 1: Add (h + 1) * f to c.
     * If cur == 0: Add h * f to c.
     * @param n
     * @return
     */
    /**
     * https://www.ucloud.cn/yun/66097.html
     * 更好的是用归纳法总结出1出现的次数的规律。
     *
     * 假设n=abcde五位数字的时候，我们分析百位c，有三种情况：
     *
     * 1）c == 0的时候，比如12013，此时百位出现1的是：00 100 ~ 00 199， 01 100~01 199，……，11 100~ 11 199，共1200个，显然这个有高位数字决定，并且受当前位数影响； 个数就是 ab*100
     *
     * 2）c == 1的时候，比如12113，此时百位出现1的肯定包括c=0的情况，另外还需要考虑低位的情况即：00100 ~ 00113共14个; 个数等于ab*100+ de + 1
     *
     * 3）c >= 2的时候，比如12213，此时百位出现1的是：00 100 ~ 00 199， 01 100~01 199，……，11 100~ 11 199，12 100 ~ 12 199，共1300个，这个有高位数字决定，其实是加一，并且乘以当前位数; 个数就是 (ab+1)*100
     *
     * 总结起来，对于一个 n = abcde 来说，百位出现1的个数计算方法为 ：
     *
     * if(c==0) ans = ab*100;
     * if(c==1) ans = ab*100+cd+1
     * if(c>1) ans = (ab+1)*100
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int c = 0;
        int f = 1;
        //just count  digit 1 on the place f
        while (f <= n)
        {
            int h = n / (f * 10);
            int cur = (n / f) % 10;
            int l = n % f;

            if (cur == 1)
            {
                c += h * f + l + 1;
            }
            else if (cur > 1)
            {
                c += (h + 1) * f;
            }
            else
            {
                c += h * f;
            }
            f *= 10;
        }
        return c;
    }

}
