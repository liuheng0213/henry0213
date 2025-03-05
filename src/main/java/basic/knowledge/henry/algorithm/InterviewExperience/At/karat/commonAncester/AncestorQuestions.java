package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester;

import basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._03binaryTree.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class AncestorQuestions {
    boolean foundP = false;
    boolean foundQ = false;

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 4}, {1, 5}, {2, 5}, {3, 6}, {6, 7}, {5, 8}, {5, 9}, {10, 2}, {10, 3}};
//        findParents(10, arr);
        boolean flag = commonAncestor(10, arr, 7, 9);
//        System.out.println(flag);
        int farthest = farestAncestor(10, arr, 5);

        System.out.println("farestAncestor ancestor: " + farthest);
    }





    private static int farestAncestor(int n, int[][] arr, int e) {
        int[] starts = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] a : arr) {
            starts[a[1]]++;
            graph[a[0]].add(a[1]);
        }

        LinkedList<Integer> queue;
        HashSet<Integer> marked;
        int max = -1;
        int res = 0;
//        for(int i = 1; i < n + 1; i++){
//            if (starts[i] == 0) {
//                res = i;
//                break;
//            }
//        }
        for (int i = 1; i < n + 1; i++) {
            if (starts[i] == 0) {
                queue = new LinkedList<>();
                marked = new HashSet<>();
                marked.add(i);
                queue.add(i);
                int depth = farthestAncestor(queue, graph, marked, e);
                if (depth != -1 && depth > max) {
                    max = depth;
                    res = i;
                }
            }
        }

        return res;
    }

    private static int farthestAncestor(LinkedList<Integer> queue, ArrayList<Integer>[] graph, HashSet<Integer> marked, int e) {
        int depth = 0;
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int poll = queue.pollFirst();
                if (poll == e) {
                    found = true;
                    return depth;
                }
                for (Integer next : graph[poll]) {
                    if (!marked.contains(next)) {
                        marked.add(next);
                        queue.add(next);
                    }
                }

            }
            depth++;
        }

        return found ? depth : -1;
    }



    public static boolean commonAncestor(int n, int[][] arr, int p, int q) {
        int[] starts = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] a : arr) {
            starts[a[1]]++;
            graph[a[0]].add(a[1]);
        }

        LinkedList<Integer> queue;
        HashSet<Integer> marked;
        for (int i = 1; i < n + 1; i++) {
            if (starts[i] == 0) {
                queue = new LinkedList<>();
                marked = new HashSet<>();
                marked.add(i);
                queue.add(i);
                if (hasCommonParents(queue, marked, graph, p, q)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasCommonParents(LinkedList<Integer> queue, HashSet<Integer> marked, ArrayList<Integer>[] graph, int p, int q) {
        boolean foundP = false;
        boolean foundQ = false;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int poll = queue.pollFirst();
                if (poll == p) {
                    foundP = true;
                }
                if (poll == q) {
                    foundQ = true;
                }
                for (Integer next : graph[poll]) {
                    if (!marked.contains(next)) {
                        marked.add(next);
                        queue.add(next);
                    }
                }

            }
        }

        return foundP && foundQ;
    }


}
