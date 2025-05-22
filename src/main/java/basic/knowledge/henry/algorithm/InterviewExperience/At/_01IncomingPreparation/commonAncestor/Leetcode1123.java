package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation.commonAncestor;

import basic.knowledge.henry.algorithm.InterviewExperience.TreeNode;

public class Leetcode1123 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).ancestor;
    }

    /**
     * dfs from cur
     find the ancestor which has  the deepest depth from cur
     * @param cur
     * @return
     */
    private ReturnType dfs(TreeNode cur) {
        if (cur == null) {
            return new ReturnType(null, 0);
        }

        ReturnType left = dfs(cur.left);
        ReturnType right = dfs(cur.right);

        if (left.depth> right.depth) {
            return new ReturnType(left.ancestor, left.depth + 1);
        }
        if (left.depth < right.depth) {
            return new ReturnType(right.ancestor, right.depth + 1);
        }
        return new ReturnType(cur, left.depth + 1);
    }
    class ReturnType{
        TreeNode ancestor;
        int depth;
        public ReturnType(TreeNode ancestor,int depth){
            this.ancestor = ancestor;
            this.depth = depth;
        }
    }
}
