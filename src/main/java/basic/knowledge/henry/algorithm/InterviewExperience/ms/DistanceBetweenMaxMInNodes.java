package basic.knowledge.henry.algorithm.InterviewExperience.ms;


import basic.knowledge.henry.algorithm.InterviewExperience.TreeNode;

public class DistanceBetweenMaxMInNodes {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(-3);
        head.left = new TreeNode(5);
        head.left.right = new TreeNode(2);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(14);
        head.left.left = new TreeNode(6);
        head.right = new TreeNode(1);
        head.right.left = new TreeNode(0);
        head.right.right = new TreeNode(8);

        DistanceBetweenMaxMInNodes distanceBetweenMaxMInNodes = new DistanceBetweenMaxMInNodes();
        distanceBetweenMaxMInNodes.getDis(head);
    }


    private TreeNode maxNode = new TreeNode(Integer.MIN_VALUE);
    private TreeNode minNode = new TreeNode(Integer.MAX_VALUE);

    public int getDis(TreeNode root) {
        // write code here
        getMaxMin(root);//找到最大最小叶子节点
        TreeNode lcaNode = getLCA(root);//找LCA
        int a = getNodeDis(lcaNode, maxNode);//最大值叶子节点到LCA的距离；
        int b = getNodeDis(lcaNode, minNode);//最小值叶子节点到LCA的距离；
        return a+b;
    }

    // 先找到最大最小叶子节点
    public void getMaxMin(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.value > maxNode.value) {
            maxNode = root;
        }
        if (root.value < minNode.value) {
            minNode = root;
        }

        getMaxMin(root.left);
        getMaxMin(root.right);
    }

    // LCA最近公共祖先
    public TreeNode getLCA(TreeNode root) {
        if (root == null) {// 说明是空树
            return null;
        }
        if (root.value == maxNode.value || root.value == minNode.value) {// 在当前树的根节点上找到两个节点之一
            return root;
        }
        TreeNode leftNode = getLCA(root.left);// 左子树中的查找两个节点并返回查找结果
        TreeNode rightNode = getLCA(root.right);// 右子树中查找两个节点并返回查找结果
        if (leftNode == null) {// 左子树中没找到，则一定在右子树上
            return rightNode;
        } else if (rightNode == null) {// 右子树没找到一定在左子树上
            return leftNode;
        } else {// 左右子树均找到一个节点，则根节点为最近公共祖先
            return root;
        }
    }

    //获取叶子节点到LCA距离
    public int getNodeDis(TreeNode lcaNode, TreeNode node){
        if(lcaNode==null){
            return -1;
        }
        if(lcaNode.value==node.value){
            return 0;
        }
        //三种情况：两个均在左子树；两个均在右子树；一左一右，所以不能用if-elseif结构
        int distance = getNodeDis(lcaNode.left, node);//左子树未找到两个节点之一
        if(distance==-1){
            distance = getNodeDis(lcaNode.right, node);
        }
        if(distance!=-1){
            return distance+1;
        }

        return -1;
    }


}
