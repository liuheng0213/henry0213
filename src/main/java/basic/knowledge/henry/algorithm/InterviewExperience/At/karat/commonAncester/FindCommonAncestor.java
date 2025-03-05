package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester;


import java.util.*;

//两个指定的点有没有公共祖先 (Time: O(N) , Space: O(N) )
public class FindCommonAncestor {
    // Function to build child-to-parent mapping from input list
    private static Map<Integer, List<Integer>> buildParentMap(int[][] inputList) {
        Map<Integer, List<Integer>> children = new HashMap<>();
        for (int[] pair : inputList) {
            int parent = pair[0];
            int child = pair[1];
            children.putIfAbsent(child, new ArrayList<>());
            children.get(child).add(parent);
        }
        return children;
    }

    // Function to retrieve all ancestors of a given node using BFS
    private static Set<Integer> getAncestors(int node, Map<Integer, List<Integer>> children) {
        Set<Integer> ancestors = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (!children.containsKey(current)) continue;
            for (int parent : children.get(current)) {
                if (!ancestors.contains(parent)) {
                    queue.add(parent);
                    ancestors.add(parent);
                }
            }
        }
        return ancestors;
    }

    // Function to check if two nodes share a common ancestor
    public static boolean commonAncestor(int[][] inputList, int a, int b) {
        Map<Integer, List<Integer>> children = buildParentMap(inputList);
        Set<Integer> ancestorsA = getAncestors(a, children);
        Set<Integer> ancestorsB = getAncestors(b, children);
        // Check for common ancestors using set intersection
        ancestorsA.retainAll(ancestorsB);
        return !ancestorsA.isEmpty();
    }

    public static void main(String[] args) {
        int[][] inputList = {
                {1, 4}, {1, 5}, {2, 5}, {3, 6}, {6, 7}
        };
        System.out.println(commonAncestor(inputList, 4, 7)); // Output: true
    }
}
