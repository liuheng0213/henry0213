package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 2->abc
 * 3->def
 * 4->ghi
 * 5->jkl
 * 6->mno
 * 7->pqrs
 * 8->tuv
 * 9->wxyz
 *
 * 此题失败再次证明，完全理解题意多么重要！！！！！！！！！
 */
public class TrieAmazon {
    HashMap<Integer,String> num2Char = new HashMap<>();
    {
        num2Char.put(2,"abc");
        num2Char.put(3,"def");
        num2Char.put(4,"ghi");
        num2Char.put(5,"jkl");
        num2Char.put(6,"mno");
        num2Char.put(7,"pqrs");
        num2Char.put(8,"tuv");
        num2Char.put(9,"wxyz");
    }
    public static void main(String[] args) {

    }

    class Trie {
        Node root;
        public List<String> search(List<Integer> nums){
            Node cur = root;
            for(int num : nums){
                if(cur.children.containsKey(num)){
                    cur = cur.children.get(num);
                }else{
                    return new ArrayList<>();
                }
            }
            List<String> res = new ArrayList<>();
            if(!cur.contents.isEmpty()){
                res.addAll(cur.contents);
            }

            if(!cur.contents.isEmpty()){
                for(int child : cur.children.keySet()){
                    dfs(cur.children.get(child),res);
                }
            }
            return res;
        }

        private void dfs(Node cur, List<String> res) {
            if(!cur.contents.isEmpty()){
                res.addAll(cur.contents);
            }
            if(cur.children.isEmpty()){
                return;
            }
            for(int child : cur.children.keySet()){
                dfs(cur.children.get(child),res);
            }
        }


    }

    class Node{
        HashMap<Integer,Node> children;
        List<String> contents;

        public Node() {
            this.children = new HashMap<>();
            this.contents = new ArrayList<>();
        }
    }
}
