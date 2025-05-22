package basic.knowledge.henry.algorithm.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {


    public static void main(String[] args) {

        Test test = new Test();


        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        List<Integer> integers = test.rightSideView(root);

        System.out.println(integers);
    }


    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i< size;i++){
                TreeNode cur = queue.poll();
                if(i == size - 1){
                    res.add(cur.val);
                }

                if(cur.left != null){
                    queue.add(cur.left);
                }

                if(cur.right != null){
                    queue.add(cur.right);
                }
            }
        }

        return res;
    }


}
