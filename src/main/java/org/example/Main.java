package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

//        new Main().recoverTree(root);
        new Main().recoverTree(root);
        System.out.println("Hello world!");
    }


    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode[] errs  =new TreeNode[2];
        TreeNode pre = null;
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if(pre != null && pre.val > cur.val){
                    errs[0] = errs[0]== null ?new TreeNode(pre.val): errs[0];
                    errs[1] = new TreeNode(cur.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }

       int val = errs[0].val;
        errs[0].val = errs[1].val;
        errs[1].val = val;

    }
    // 前序遍历，始于cur, 遇见x, 或Y 就交换
    private void reverse(TreeNode cur,int x, int y){
        if(cur == null){
            return;
        }

        if(cur.val == x || cur.val == y){
            cur.val = cur.val == x ? y : x;
        }

        reverse(cur.left,x,y);
        reverse(cur.right,x,y);
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }