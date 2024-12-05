package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1268 {
    public static void main(String[] args) {
        Leetcode1268 leetcode1268 = new Leetcode1268();
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        List<List<String>> lists = leetcode1268.suggestedProducts(products, searchWord);
        System.out.println(lists);
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        //sort words so they will be added in a sorted order to nodes
        Arrays.sort(products);

        Node root = new Node();
        for(String str : products){
            char[] chs = str.toCharArray();
            Node cur = root;
            for(int i =0;i< chs.length;i++ ){
                int idx = chs[i] - 'a';
                if(cur.next[idx] == null){
                    cur.next[idx] = new Node();
                }
                cur = cur.next[idx];

                if(cur.words.size()< 3){
                    cur.words.add(str);
                }
            }

        }
        Node cur = root;

        List<List<String>> res = new ArrayList<>();
        for(int i =0;i< searchWord.length();i++){
            cur = cur.next[searchWord.charAt(i)- 'a'];

            if(cur != null){
                List<String> sub = cur.words;
                res.add(sub);
            }else{
                while(i< searchWord.length()){
                    res.add(new ArrayList<>());
                    i++;
                }
                break;
            }
        }
        return res;

    }
    //trie node
    class Node {
        Node[] next;
        List<String> words;
        Node() {
            words = new ArrayList();
            next = new Node[26];
        }
    }


}
