package basic.knowledge.henry.algorithm.InterviewExperience.rk;

import java.util.*;

public class Leetcode49 {
    public static void main(String[] args) {
        Leetcode49 leetcode49 = new Leetcode49();
        String s = leetcode49.reverseWords("a good   example");
        String str = "s/r/ty";
        String[] words = str.split("/");
        System.out.println();

        int[] cur = new int[]{1,2,3};
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(cur[0],cur[1]));
    }
    public String reverseWords(String s) {
        // 去空格，分割字符串
        String[] words = s.trim().split(" ");
        StringBuilder result = new StringBuilder();
        // 从后向前遍历单词数组
        for (int i = words.length - 1; i >= 0; i--) {
            String removal = removeBlank(words[i]);
            if (removal.equals(""))
                continue;
            result.append(removeBlank(words[i])).append(" ");
        }
        return result.toString().trim();
    }

    private String removeBlank(String str){
        if(str.equals("")){
            return str;
        }
        while(str.charAt(0) == ' ' || str.charAt(str.length() - 1) == ' '){
            str.trim();
        }
        return str;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Node,List<String>> map = new HashMap<>();
        for(String str : strs){
            Node encoded = encode2Node(str);
            List<String> anagrams = map.computeIfAbsent(encoded,k-> new ArrayList<>());
            anagrams.add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<Node,List<String>> entry : map.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }




    class Node{
        int[] chs;
        public Node(){
            chs = new int[26];
        }
        private void update(String str){
            for(int i =0;i< str.length();i++){
                char c = str.charAt(i);
                chs[c- 'a']++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Arrays.equals(chs, node.chs);
        }

        @Override
        public int hashCode() {
            int result = 1;
            for (int element : chs)
                result = 31 * result + element;

            return result;
//            return Arrays.hashCode(chs);
        }
    }
    private Node encode2Node(String str){
       Node node = new Node();
       node.update(str);
       return node;
    }

    private String encode(String str){
        char[] chs = new char[26];
        for(int i =0;i< str.length();i++){
            char c = str.charAt(i);
            chs[c- 'a']++;
        }

        return new String(chs);
    }
}
