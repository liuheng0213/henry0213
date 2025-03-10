package basic.knowledge.henry.algorithm.InterviewExperience.At.commonAncester;

import java.util.*;


//找出只有一个parent 和只有0个parent的节点
public class Find0_1Ancestor {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 4}, {1, 5}, {2, 5}, {3, 6}, {6, 7}};
        Find0_1Ancestor obj = new Find0_1Ancestor();
        List<Integer> parents = obj.findParents(arr);
        System.out.println(parents);
    }

    //只有一个parent 和只有0个parent的节点
    // o parent 1, 2, 3
    // 1 个parent 4, 5 ,6
    public  List<Integer> findParents(int[][] arr) {
        HashMap<Integer,Integer> node2Count = new HashMap<>();
        for (int[] a : arr) {
            int start = a[0];
            int end = a[1];
            node2Count.put(end,node2Count.getOrDefault(end,0)+1);
            node2Count.put(start,0);
        }

        List<Integer> ans = new ArrayList<>();
        for(Integer key : node2Count.keySet()){
            if(node2Count.get(key) == 1 || node2Count.get(key) == 0){
                ans.add(key);
            }
        }

        return ans;
    }


}
