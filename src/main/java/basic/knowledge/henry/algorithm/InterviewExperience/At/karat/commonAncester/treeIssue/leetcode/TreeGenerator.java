package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester.treeIssue.leetcode;

import basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester.treeIssue.leetcode.TreeNode;

public class TreeGenerator {
    public static TreeNode createTree(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        return root;
    }


}
