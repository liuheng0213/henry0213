package basic.knowledge.henry.algorithm.InterviewExperience.At.pqIssues;

import java.util.PriorityQueue;

public class Leetcode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b)->a.val-b.val);
        for(ListNode h : lists){
            if(h != null){
                pq.add(h);
            }
        }

        ListNode res = new ListNode(-666);
        ListNode p = res;
        while(!pq.isEmpty()){
            ListNode n =pq.poll();
            p.next = n;
            if (n.next != null) {
                pq.add(n.next);
            }
            p = p.next;
        }
        return res.next;

    }
}
