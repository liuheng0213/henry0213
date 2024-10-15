package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Leetcode373 {
    public static void main(String[] args) {

        System.out.println(500 + 1030);
        int[] nums1 = new int[]{1,2,4,5,6};
        int[] nums2 = new int[]{3,5,7,9};
        int k =3;

        //[[1,3],[2,3],[1,5]]
        List<List<Integer>> res = new Leetcode373().kSmallestPairs(nums1, nums2, k);
        System.out.println("hello world");
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 存储三元组 (num1[i], nums2[i], i)
        // i 记录 nums2 元素的索引位置，用于生成下一个节点
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // 按照数对的元素和升序排序
            return (a[0] + a[1]) - (b[0] + b[1]);
        });
        // 按照 23 题的逻辑初始化优先级队列
        for (int i = 0; i < nums1.length; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }

        List<List<Integer>> res = new ArrayList<>();
        // 执行合并多个有序链表的逻辑
        while (!pq.isEmpty() && k > 0) {
            int[] cur = pq.poll();
            k--;
            // 链表中的下一个节点加入优先级队列
            int next_index = cur[2] + 1;
            if (next_index < nums2.length) {
                pq.add(new int[]{cur[0], nums2[next_index], next_index});
            }

            List<Integer> pair = new ArrayList<>();
            pair.add(cur[0]);
            pair.add(cur[1]);
            res.add(pair);
        }
        return res;
    }
}