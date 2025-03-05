package basic.knowledge.henry.algorithm.InterviewExperience.rk.calculators;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode772 {
    public int calculateHardwithEmbeddedBracket(String s) {
        Map<Integer,Integer> indexPair= new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for(int i =0;i< s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(i);
            }else if(ch == ')'){
                indexPair.put(st.pop(),i);
            }
        }
        return calculateWithinRange(s,0,s.length() - 1,indexPair);
    }


    private int calculateWithinRange(String s,int start,int end,Map<Integer,Integer> indexPair) {

        Stack<Integer> st = new Stack<>();

        int num = 0;
        char sign = '+';
        for(int i =start;i<= end;i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '('){
                // System.out.println(" (: " + (i+1) + "===): " + (indexPair.get(i) -1));
                num = calculateWithinRange(s,i+1,indexPair.get(i) -1,indexPair);
                // System.out.println(" tmp " + num);
                i = indexPair.get(i);
                // System.out.println(" last i " + i);
            }

            if(i == end || ch == '*' || ch == '/' || ch == '+' || ch == '-'){
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
}
