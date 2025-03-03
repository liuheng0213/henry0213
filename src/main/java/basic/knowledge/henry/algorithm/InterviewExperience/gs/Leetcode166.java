package basic.knowledge.henry.algorithm.InterviewExperience.gs;

import java.util.HashMap;

public class Leetcode166 {
    public static void main(String[] args) {
        Leetcode166 leetcode166 = new Leetcode166();
        String s = leetcode166.fractionToDecimal(7, -12);
        System.out.println(s);

    }
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        StringBuilder str = new StringBuilder();

        if( (numerator<0 &&denominator>0) || (numerator>0 && denominator<0))
            str.append('-');

        Long n = Math.abs(Long.valueOf(numerator));
        Long d = Math.abs(Long.valueOf(denominator));
        Long num = n / d;
        Long mod = n % d;
        str.append(num);
        if(mod == 0){
            return str.toString();
        }
        str.append(".");
        HashMap<Long,Integer> modToLen = new HashMap<>();

        while(!modToLen.containsKey(mod)){
            modToLen.put(mod,str.length());
            num = mod * 10 / d;
            str.append(num);
            mod = mod * 10 % d;
            if(mod == 0){
                return str.toString();
            }
        }

        str.insert(modToLen.get(mod),"(");
        str.insert(str.length(),")");

        return str.toString();

    }

}
