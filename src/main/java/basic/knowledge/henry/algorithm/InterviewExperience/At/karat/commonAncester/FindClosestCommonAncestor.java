package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindClosestCommonAncestor {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{0,2},{0,3},{1, 4}, {1, 5}, {2,
                5}, {3, 6}, {6, 7}, {5, 8}, {5, 9}, {2, 6}};
        //        findParents(10, arr);

        //        System.out.println(flag);
        int farthest = new FindClosestCommonAncestor().findClosestCommonAncestor(0,arr, 5,6);
    }

    private  int findClosestCommonAncestor(int start, int[][] arr, int a, int b) {
        Map<Integer, List<Integer>> integerListMap = buildParentMap(arr);
       boolean finda =  helper(integerListMap,start,a);
       boolean findb =  helper(integerListMap,start,b);
        int res = finda && findb? start: -1;
        for(int next : integerListMap.get(start)){
            finda = helper(integerListMap,next,a);
            findb = helper(integerListMap,next,b);
            res = finda && findb ?next :res;
        }

        return res;
    }

    /**
     * from start, find the closest common ancestor in the graph
     * @param graph
     * @param start
     * @param target
     * @return
     */
    private boolean helper(Map<Integer, List<Integer>> graph, int start, int target) {
        return false;
    }


    //from up to down
    private  Map<Integer, List<Integer>> buildParentMap(int[][] inputList) {
        Map<Integer, List<Integer>> children = new HashMap<>();
        for (int[] pair : inputList) {
            int parent = pair[0];
            int child = pair[1];
            children.putIfAbsent(parent, new ArrayList<>());
            children.get(parent).add(child);
        }
        return children;
    }
}
