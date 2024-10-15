package basic.knowledge.henry.algorithm.InterviewExperience.ms;


import basic.knowledge.henry.algorithm.InterviewExperience.TreeNode;

/**
 * 给一个binary tree，让你找所有的是BST的子树当中，
 * size最大的那个，返回这个子树的root和树的size。
 */
public class BSTSize {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(6);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(3);


        node.right = new TreeNode(12);
        node.right.left = new TreeNode(10);
        node.right.right = new TreeNode(13);
        node.right.right.left = new TreeNode(20);
        node.right.right.right = new TreeNode(16);

        node.right.left.left = new TreeNode(4);
        node.right.left.right = new TreeNode(14);

        node.right.left.left.left = new TreeNode(2);
        node.right.left.left.right = new TreeNode(5);
        node.right.left.right.left = new TreeNode(11);
        node.right.left.right.right = new TreeNode(15);
        BSTSize obj = new BSTSize();
        TreeNode res = obj.process(node).maxBSTHead;
        System.out.println(res.value);
    }
    private Data process(TreeNode node) {
        if (node == null) {
            return new Data(null, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        Data leftData = process(node.left);
        Data rightData = process(node.right);
        int min = Math.min(Math.min(leftData.min, rightData.min), node.value);
        int max = Math.max(Math.max(leftData.max, rightData.max), node.value);
        int maxBSTSize = Math.max(leftData.maxBSTSize, rightData.maxBSTSize);
        TreeNode maxBSTHead = leftData.maxBSTSize > rightData.maxBSTSize ? leftData.maxBSTHead : rightData.maxBSTHead;
        if (node.left == leftData.maxBSTHead && node.right == rightData.maxBSTHead
                && node.value >= leftData.max && node.value <= rightData.min) {
            maxBSTHead = node;
            maxBSTSize = leftData.maxBSTSize + rightData.maxBSTSize + 1;
        }
        return new Data(maxBSTHead, min, max, maxBSTSize);
    }

    class Data {
        TreeNode maxBSTHead;
        int min;
        int max;
        int maxBSTSize;

        public Data(TreeNode maxBSTHead, int min, int max, int maxBSTSize) {
            this.maxBSTHead = maxBSTHead;
            this.min = min;
            this.max = max;
            this.maxBSTSize = maxBSTSize;
        }
    }

}
