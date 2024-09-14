package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._02linkedList;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
         node.next = new ListNode(2);
        node.next.next= new ListNode(3);

        int[] arr1 = new int[]{0,0,1,1,1,1,2,3,3};
        int[] arr2 = new int[]{0,0,0,0,1,1,2,3,3};
        int[] arr3 = new int[]{1,2};
        new Solution().removeDuplicates(arr1);

    }
    public int removeDuplicates(int[] nums) {
        int index = 1;


        for(int i = 1;i< nums.length;i++){
            if(nums[i] != nums[i - 1]){
                nums[index++] = nums[i];
            }


        }

        return index;
    }
}

