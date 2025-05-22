package basic.knowledge.henry.algorithm.InterviewExperience.tt;

import basic.knowledge.henry.algorithm.InterviewExperience.ListNode;

public class ReverseListNode {
    public static void main(String[] args) {
        ListNode node =  new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);


        ListNode res = new ReverseListNode().reverse2(node);
        System.out.println(res);
    }
    private ListNode reverse2(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;

        while(cur != null){
            if(cur.next != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }else{
                cur.next = pre;
                return cur;
            }
        }

        return null;
    }
    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode last = reverse(node.next);

        node.next.next = node;

        node.next = null;

        return last;
    }


}
