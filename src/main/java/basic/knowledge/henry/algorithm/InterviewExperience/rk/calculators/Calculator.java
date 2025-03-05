package basic.knowledge.henry.algorithm.InterviewExperience.rk.calculators;

import java.util.*;

public class Calculator {

    /**
     * 只有加减 没有括号
     * @param s
     * @return
     */
    public int calculator1(String s) {
        if (s == null || s.isEmpty()) return 0;

        int sign = 1, res = 0;
        StringBuilder digits = new StringBuilder();
        int num = 0;

        for (int i =0;i< s.length();i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else {
                res += sign * num;
                num =0;
                sign = ch == '+' ? 1:-1;
            }

            if(i == s.length() - 1){
                res += sign * num;
            }
        }

        return res;
    }

    /**
     * 有括号
     * @param s
     * @return
     */
    public int calculator2(String s) {
        if (s == null || s.isEmpty()) return 0;

        int sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        StringBuilder digits = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.append(ch);
            } else {
                if (digits.length() > 0) {
                    res += sign * Integer.parseInt(digits.toString());
                    digits.setLength(0);
                }
                if (ch == '+') sign = 1;
                else if (ch == '-') sign = -1;
                else if (ch == '(') {
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    sign = 1;
                } else if (ch == ')') {
                    res = res * stack.pop() + stack.pop();
                }
            }
        }

        if (digits.length() > 0) {
            res += sign * Integer.parseInt(digits.toString());
        }

        return res;
    }

    /**
     * 有符号
     * @param s
     * @param d
     * @return
     */
    public String calculator3(String s, Map<Character, Integer> d) {
        if (s == null || s.isEmpty()) return "0";

        int sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        List<String> alphas = new ArrayList<>();
        StringBuilder digits = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (d.containsKey(ch)) {
                    res += sign * d.get(ch);
                } else {
                    alphas.add((sign == 1 ? "+" : "-") + ch);
                }
            } else if (Character.isDigit(ch)) {
                digits.append(ch);
            } else {
                if (digits.length() > 0) {
                    res += sign * Integer.parseInt(digits.toString());
                    digits.setLength(0);
                }
                if (ch == '+') sign = 1;
                else if (ch == '-') sign = -1;
                else if (ch == '(') {
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    sign = 1;
                } else if (ch == ')') {
                    res = res * stack.pop() + stack.pop();
                }
            }
        }

        if (digits.length() > 0) {
            res += sign * Integer.parseInt(digits.toString());
        }

        StringBuilder result = new StringBuilder(String.valueOf(res));
        for (String al : alphas) {
            result.append(al);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Calculator sol = new Calculator();
        System.out.println(sol.calculator1("7+13-4")); // 16
        System.out.println(sol.calculator2("-(1+2+3)+5")); // -1
        System.out.println(sol.calculator2("-(1-(2+3)+2)+5")); // 7
        Map<Character, Integer> d = new HashMap<>();
        d.put('a', 5);
        System.out.println(sol.calculator3("-(1-(2+3)+2)+a", d)); // 7
        d.put('a', 2);
        System.out.println(sol.calculator3("-(1-(a+3)+a)+5", d)); // 7
        d.clear();
        d.put('a', 2);
        d.put('c', 0);
        d.put('b', 0);
        System.out.println(sol.calculator3("c-(1-(a+3)+a)-b", d)); // 7
    }
}
