package basic.knowledge.henry.algorithm.InterviewExperience.rk.calculators;

import java.util.Stack;

public class Leetcode227 {
    private int calculateNoBracketOrNoMultiplication(String s){
        Stack<Integer> st = new Stack<>();

        int num = 0;
        char sign = '+';
        for(int i =0;i< s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '+' || ch == '-'){
                if(sign == '+'){
                    st.push(num);
                }else if(sign == '-'){
                    st.push(0-num);
                }
                sign = ch;
                num = 0;
            }
            if(i == s.length() -1){
                if(sign == '+'){
                    st.push(num);
                }else if(sign == '-'){
                    st.push(0-num);
                }
            }
        }

        int res = 0;
        while(!st.isEmpty()){
            res+= st.pop();
        }

        return res;
    }


    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();

        int num = 0;
        char sign = '+';
        for(int i =0;i< s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '*' || ch == '/' || ch == '+' || ch == '-'){
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
            if(i == s.length() -1){
                if(sign == '*'){
                    st.push(st.pop() * num);
                }else if(sign == '/'){
                    st.push(st.pop() / num);
                }else if(sign == '+'){
                    st.push(num);
                }else if(sign == '-'){
                    st.push(0-num);
                }
            }
        }

        int res = 0;
        while(!st.isEmpty()){
            res+= st.pop();
        }

        return res;
    }
}
