package basic.knowledge.henry.algorithm.InterviewExperience.rk;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorLc227_772 {

    public static void main(String[] args) {
        CalculatorLc227_772 calculator = new CalculatorLc227_772();
        String str = "(1+ (6 -3) * 15 - 8)/ (3 -1)";
        String str2= "1+ 2 - 3 + 12-10";
        System.out.println(calculator.calculateNoBracketOrNoMultiplication(str2));
        int calculate1 = calculator.calculateHardwithEmbeddedBracket(str);
        int calculate2 = calculator.calculatewithoutEmbeddedBrackets(str);

        System.out.println(calculate1);
        System.out.println(calculate2);
    }

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

    private int calculatewithoutEmbeddedBrackets(String s){
        int start = -1;
        int end = -1;
        boolean inBracket = false;
        StringBuilder sb = new StringBuilder();
        for(int i =0;i< s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                start = i;
                inBracket = true;
            }else if(ch == ')'){
                end = i;
                inBracket = false;
            }else if(!inBracket){
                sb.append(ch);
            }

            if(start != -1 && end != -1){
                int res = calculate(s.substring(start + 1,end));
                sb.append(res);
                start = -1;
                end = -1;
            }
        }
        return calculate(sb.toString());
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
