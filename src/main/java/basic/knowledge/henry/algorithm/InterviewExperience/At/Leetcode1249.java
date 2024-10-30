package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.Stack;

public class Leetcode1249 {
    public String minRemoveToMakeValid(String s) {

        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') {
                st.add(i);
            }
            if (sb.charAt(i) == ')') {
                if (!st.empty()) st.pop();
                else sb.setCharAt(i, '*'); //placeholder, ready to me removed
            }
        }
        while (!st.empty())
            sb.setCharAt(st.pop(), '*');//placeholder, ready to me removed
        return sb.toString().replaceAll("\\*", "");
    }
}
