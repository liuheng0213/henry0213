package basic.knowledge.henry.algorithm.InterviewExperience.rk;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Leetcode388 {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);

        Iterator<Integer> iterator = st.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Leetcode388 leetcode388 = new Leetcode388();
        int res = leetcode388.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
        System.out.println(res);
    }

    public int lengthLongestPath(String input) {
        // 这个栈存储之前的父路径。实际上这里只用存父路径的长度就够了，这个优化留给你吧
        Deque<Integer> stack = new LinkedList<>();
        int maxLen = 0;
        String[] split = input.split("\n");
        for (String part : split) {
            int level = part.lastIndexOf("\t") + 1;
            // 让栈中只保留当前目录的父路径
            while (level < stack.size()) {
                stack.removeLast();
            }
            stack.addLast(part.substring(level).length());
            // 如果是文件，就计算路径长度
            if (part.contains(".")) {
                int sum = stack.stream().reduce(Integer::sum).orElse(0);
                // 加上父路径的分隔符
                sum += stack.size() - 1;
                maxLen = Math.max(maxLen, sum);
            }
        }
        return maxLen;
    }
}

