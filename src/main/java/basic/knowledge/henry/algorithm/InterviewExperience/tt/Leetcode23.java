package basic.knowledge.henry.algorithm.InterviewExperience.tt;

import basic.knowledge.henry.algorithm.InterviewExperience.ListNode;

public class Leetcode23 {
    public static void main(String[] args) {
        ListNode[] lists = new ListNode[2];
        lists[0] = null;
        lists[1] = new ListNode(1);
        new Leetcode23().mergeKLists(lists);

    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        return sort(lists,0,lists.length-1);
    }

    private ListNode sort(ListNode[] lists,int start,int end){
        if(start == end){
            return lists[start];
        }
        int mid = (start + end)/2;
        ListNode left = sort(lists,start,mid);
        ListNode right = sort(lists,mid+1,end);

        return mergeKLists(left,right);
    }

    private ListNode mergeKLists(ListNode left,ListNode right){
        if(left == null && right == null){
            return right;
        }
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        ListNode dummy = new ListNode(-1000);
        ListNode p = dummy;
        while(left!= null || right != null){
            if(left == null && right != null){
                p.next = right;
                right = right.next;
            }else if(left != null && right == null){
                p.next = left;
                left = left.next;
            }else{
                if(left.value<right.value){
                    p.next = left;
                    left = left.next;
                }else{
                    p.next = right;
                    right = right.next;
                }
            }
            p = p.next;
        }

        return dummy.next;
    }
}
