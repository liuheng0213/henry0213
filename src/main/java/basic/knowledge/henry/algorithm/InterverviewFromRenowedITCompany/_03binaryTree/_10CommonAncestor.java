package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._03binaryTree;

/**
 * 在二叉树中找到两个节点得最近公共祖先
 */
public class _10CommonAncestor {
    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(5);
        head.left.right = new Node(2);
        head.left.right.left = new Node(7);
        head.left.right.right = new Node(4);
        head.left.left = new Node(6);
        head.right = new Node(1);
        head.right.left = new Node(0);
        head.right.right = new Node(8);

        _10CommonAncestor obj = new _10CommonAncestor();

        Node node = obj.lowestCommonAncestor_2(head,head.left,  head.left.left);
        System.out.println(node.value);
    }

    /**
     * under the head, o1 find its own nearest ancestor, or o2 find its nearest ancestor
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public Node lowestCommonAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        // leftAncestor is o1 or o2 it self
        Node leftAncestor = lowestCommonAncestor(head.left,o1,o2);
        Node rightAncestor = lowestCommonAncestor(head.right,o1,o2);

        System.out.println((leftAncestor== null ? 0 : leftAncestor.value) + "---" + (rightAncestor == null ? 0 :rightAncestor.value));
        System.out.println("=======");
        //leftAncestoro1 's ancestor is head.left &&rightAncestor is rightAncestor
        if(leftAncestor != null && rightAncestor != null){
            return head;
        }
        return leftAncestor != null ? leftAncestor : rightAncestor;
    }


    boolean foundP = false, foundQ = false;

    /**
     * 如果不确定p q,一定在树里
     * @param root
     * @param q
     * @return
     */
    Node lowestCommonAncestor_2(Node root, Node p, Node q) {
        Node res = find(root, p.value, q.value);
        if (!foundP || !foundQ) {
            return null;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return res;
    }

    // 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
    Node find(Node root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // 后序位置，判断当前节点是不是目标值

        Node left = find(root.left, val1, val2);
        Node right = find(root.right, val1, val2);

        //三种情况： 1 left right  公用一个祖先 2 left 自己是right的祖先 3 right 是 left的祖先
        // 后序位置，判断当前节点是不是 LCA 节点
        if (left != null && right != null) {
            return root;
        }

        if (root.value == val1 || root.value == val2) {
            // 找到了，记录一下
            if (root.value == val1) foundP = true;
            if (root.value == val2) foundQ = true;
            return root;
        }


        return left != null ? left : right;
    }


}
