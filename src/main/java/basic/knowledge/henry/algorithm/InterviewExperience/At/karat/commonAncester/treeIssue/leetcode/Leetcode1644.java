package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester.treeIssue.leetcode;


/**
 * p 和 q 不一定存在于树中，所以你不能遇到一个目标值就直接返回，
 * 而应该对二叉树进行完全搜索（遍历每一个节点），如果发现 p 或 q 不存在于树中，那么是不存在 LCA 的。
 */
public class Leetcode1644 {
    boolean foundQ = false;
    boolean foundP = false;
    public static void main(String[] args) {
        TreeNode root = TreeGenerator.createTree();
        Leetcode1644 obj = new Leetcode1644();
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        TreeNode treeNode = obj.lowestCommonAncestor(root.left, p, q);
        System.out.println(treeNode.val);
    }
    // 用于记录 p 和 q 是否存在于二叉树中


    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find(root, p.val, q.val);
        if (!foundP || !foundQ) {
            return null;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return res;
    }

    /**
     *   * definition:
     *      * from root to traverse ,after traverse, if root is equal to p or q(if p is the ancestor of q ,
     *      it returns p, and update foundQ and foundP) , return root,
     *      * otherwise find the closest common ancestor for  p, q
     * @param root
     * @param val1
     * @param val2
     * @return
     */
    // 在二叉树中寻找 val1 和 val2 的最近公共祖先节点;为什么要后遍历全树， 请看236关于find方法的定义
    TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);
        // 后序位置，判断当前节点是不是目标值
        if (root.val == val1 || root.val == val2) {
            // 找到了，记录一下
            if (root.val == val1) foundP = true;
            if (root.val == val2) foundQ = true;
            return root;
        }
        // 后序位置，判断当前节点是不是 LCA 节点
        if (left != null && right != null) {
            return root;
        }



        return left != null ? left : right;
    }
}
