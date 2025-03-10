package basic.knowledge.henry.algorithm.InterviewExperience.At.commonAncester.treeIssue.leetcode;

/**
 * 一定有p, q 从root出发找到最近祖先. 👎P, q 一定从p q 出发找得到
 */
public class Leetcode236 {
    public static void main(String[] args) {
        TreeNode root = TreeGenerator.createTree();
        Leetcode236 obj = new Leetcode236();
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(4);
        TreeNode treeNode = obj.lowestCommonAncestor(root, p, q);
//        System.out.println(treeNode.val);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p.val, q.val);
    }

    /**
     * definition:
     * from root to traverse ,if root is equal to p or q(if p is the ancestor of q , it returns p) , return root,
     * otherwise find the closest common ancestor for  p, q
     * and the above situations are both returning the closest ancestor
     *
     * 引出设计递归函数的新的思路： 可以根据if else 设计返回不同的解
     * @param root
     * @param p
     * @param q
     * @return
     */
    private TreeNode find(TreeNode root, int p, int q) {
        if(root == null) {
            return null;
        }

        // find p or q if searching from root
        if(root.val == p || q == root.val){
            System.out.println(root.val);
            return root;
        }


        TreeNode left = find(root.left,p,q);
        TreeNode right = find(root.right,p,q);

        //p,q has the common ancestor which are left and right ,
        //but they are different. so the common ancestor is root
        //p or q is under left or right
        if(left != null && right!= null){
            return root;
        }

        //p q both are under left or right
        return left!= null ? left : right;
    }


}
