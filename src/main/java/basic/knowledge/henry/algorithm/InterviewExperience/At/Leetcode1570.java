package basic.knowledge.henry.algorithm.InterviewExperience.At;


import java.util.HashMap;
import java.util.Map;

public class Leetcode1570 {

    Map<Integer, Integer> map;
    Leetcode1570(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) map.put(i, nums[i]);
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(Leetcode1570 vec) { // this vec is just for nums1!
        int res = 0;
        // choose the smaller map to traverse
        Map<Integer, Integer> m1 = map.size() < vec.map.size() ? map : vec.map;
        Map<Integer, Integer> m2 = map.size() >= vec.map.size() ? map : vec.map;

        // calculate
        for (int key : m1.keySet()) {
            if (m2.containsKey(key)) {
                res += m1.get(key) * m2.get(key);
            }
        }

        return res;
    }
}
