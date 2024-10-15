package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.LinkedList;
import java.util.Stack;

public class Leetcode227 {
    public static void main(String[] args) {
        Leetcode227 leetcode227 = new Leetcode227();
        int calculate = leetcode227.calculate("3/2");
        System.out.println(calculate);
    }


    public int calculate(String s) {
       Stack<Integer> st = new Stack<>();

        int num = 0;
        char sign = '+';
        for(int i =0;i< s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }

            if(i == s.length() - 1 || ch == '*' || ch == '/' || ch == '+' || ch == '-'){
                if(sign == '*'){
                    st.push(st.pop() * num);
                }else if(sign == '/'){
                    st.push(st.pop() / num);
                }else if(sign == '+'){
                    st.push(num);
                }else if(sign == '-'){
                    st.push(0-num);
                }
                sign = ch;
                num = 0;
            }


        }
        int res = 0;
        while(!st.isEmpty()){
            res+= st.pop();
        }

        return res;
    }


    int calculate_2(String s) {
        Stack<Integer> stk = new Stack<>();
        // 记录算式中的数字
        int num = 0;
        // 记录 num 前的符号，初始化为 +
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            //为什么这里不能用else if , 因为有个条件i == s.length() - 1 也有可能这个字符为数字
            if (c == '+' || c == '-' || c == '/' || c == '*' || i == s.length() - 1) {
                int pre;
                switch (sign) {
                    case '+':
                        stk.push(num); break;
                    case '-':
                        stk.push(-num); break;
                    // 只要拿出前一个数字做对应运算即可
                    case '*':
                        pre = stk.pop();
                        stk.push(pre * num);
                        break;
                    case '/':
                        pre = stk.pop();
                        stk.push(pre / num);
                        break;
                }
                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }
        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stk.isEmpty()) {
            res += stk.pop();
        }
        return res;
    }
}



