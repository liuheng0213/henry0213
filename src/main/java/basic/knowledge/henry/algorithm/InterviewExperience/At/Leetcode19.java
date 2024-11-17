package basic.knowledge.henry.algorithm.InterviewExperience.At;


import basic.knowledge.henry.algorithm.InterviewExperience.ListNode;

//Given the head of a linked list, remove the nth node from the end of the list and return its head.
public class Leetcode19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        for(int i =0;i< n;i++){
            p1 = p1.next;
        }
        ListNode p2 = head;
        while(p1 != null){
            p2 = p2.next;
            p1 = p1.next;
        }

        // p2 is at the position of n from the end of the list
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur != null && cur.next != null) {
            if(cur.next == p2){
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }


        return dummy.next;
    }
}
