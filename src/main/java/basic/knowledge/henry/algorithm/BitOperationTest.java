package basic.knowledge.henry.algorithm;

import java.util.TreeSet;

/**
 * 位运算运用
 */
public class BitOperationTest {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        Integer floor = set.floor(2);
        System.out.println(floor);
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        test6();
    }

    /**
     * 异或  相同得异或 = 0 ; 0 异或x = x
     */
    private static void test6() {
        int x = 5;
        int y = 6;
        System.out.println("1---> : " + (x ^ x));// 0 
        System.out.println("2---> : " + (0 ^ x));// x
        System.out.println("3---> : " + (x ^ x ^ x));//x
        System.out.println("4---> : " + (x ^ y));
        System.out.println("5---> : " + (x ^ y ^ x));// x ^ x ^ y = y
        System.out.println("=============================");
        System.out.println(3 ^ 2 ^ 1);
    }

    private static void test5() {
        int i = 5;
        System.out.println(i >> 2);
        System.out.println(i << 2);
    }

    /**
     * 取模
     */
    private static void test4() {
        // 19 除以 8 的模
        System.out.println((19 & (8 - 1)) == 19 % 8);
        System.out.println(19 & 0x7FFFFFFF);

    }

    /**
     * 求平均值
     */
    private static void test3() {
        int x = 1800;
        int y = 1800;
        int avg = (x & y) + ((x ^ y) >> 1);
        System.out.println(avg);
    }

    /**
     * 判断n是否是2的正整数幂
     */
    private static void test2() {
        int n = 1024;
        System.out.println((n & n - 1) == 0 && n != 0);
    }

    /**
     * n & 1 == 1 为奇数; ==0 为偶数
     */
    private static void test1() {
        int i = 36;
        System.out.println(i & 1);
        int j = 39;
        System.out.println(j & 1);
    }
}
