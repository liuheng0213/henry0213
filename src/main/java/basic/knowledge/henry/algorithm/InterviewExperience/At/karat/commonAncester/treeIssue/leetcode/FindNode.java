package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester.treeIssue.leetcode;


//给你输入一棵没有重复元素的二叉树根节点 root 和一个目标值 val，请你写一个函数寻找树中值为 val 的节点。
public class FindNode {

    public static void main(String[] args) {
        TreeNode root = TreeGenerator.createTree();
        FindNode findNode = new FindNode();
        TreeNode res = findNode.findPostOrder(root, 5);
        System.out.println(res.val);
    }

    TreeNode findPostOrder(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        // 先去左右子树寻找
        TreeNode left = findPostOrder(root.left, val);
        TreeNode right = findPostOrder(root.right, val);
        // 后序位置，看看 root 是不是目标节点
        if (root.val == val) {
            return root;
        }
        // root 不是目标节点，再去看看哪边的子树找到了
        return left != null ? left : right;
    }


    TreeNode findPreOrder(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        // 前序位置
        if (root.val == val) {
            return root;
        }
        // root 不是目标节点，去左右子树寻找
        TreeNode left = findPreOrder(root.left, val);
        TreeNode right = findPreOrder(root.right, val);
        // 看看哪边找到了
        return left != null ? left : right;
    }

}
