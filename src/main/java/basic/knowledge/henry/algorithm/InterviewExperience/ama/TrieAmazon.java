package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.ArrayList;
import java.util.HashMap;
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
 */
public class TrieAmazon {
    public static void main(String[] args) {

    }

    class Trie {
        Node root;
        public List<String> search(List<Integer> nums){
            Node cur = root;
            for(int num : nums){
                if(cur.children.containsKey(num)){
                    cur = cur.children.get(num);
                }
            }
            return null;
        }
    }

    class Node{
        HashMap<Integer,Node> children;
        List<Character> values;
        List<String> contents;

        public Node(List<Character> values) {
            this.values = values;
            this.children = new HashMap<>();
            this.contents = new ArrayList<>();
        }
    }
}
