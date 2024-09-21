package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.HashSet;

public class Leetcode720 {
    public static void main(String[] args) {
        String[] words = new String[]{"a","banana","app","appl","ap","apply","apple"};
        Leetcode720 leetcode720 = new Leetcode720();
        String s = leetcode720.longestWord(words);
        System.out.println(s);
    }
    private TreeNode root;
    private String result = "";

    public String longestWord(String[] words) {
        root = new TreeNode();

        for (String w : words)
            insert(w);

        dfs(root);

        return result;
    }

    private void dfs(TreeNode node) {
        if (node == null)
            return;

        if (node.word != null) {
            if (node.word.length() > result.length())
                result = node.word;
            else if (node.word.length() == result.length() && node.word.compareTo(result) < 0)
                result = node.word;
        }

        for (TreeNode child : node.children)
            if (child != null && child.word != null)
                dfs(child);
    }

    private void insert(String word) {
        TreeNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null)
                current.children[c - 'a'] = new TreeNode();
            current = current.children[c - 'a'];
        }
        current.word = word;
    }

}

class TreeNode {
    TreeNode[] children = new TreeNode[26];
    String word;

    TreeNode () {}
}