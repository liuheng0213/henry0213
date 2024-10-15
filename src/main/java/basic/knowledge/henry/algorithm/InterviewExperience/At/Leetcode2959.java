package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;


/**
 * Sure! The writer used an integer type to represent the set because an integer's binary representation provides a convenient and efficient way to handle subsets of a set of nodes.
 * Here's a detailed explanation of what the set actually is and why this approach is used:
 *
 * Binary Representation of Sets
 * Binary Representation:
 *
 * An integer in binary form consists of bits (0s and 1s). Each bit position can be used to represent whether a specific element (or node) is included in a subset.
 * For example, if there are n nodes, an integer with n bits can represent any subset of these nodes.
 * Using Bits to Represent Nodes:
 *
 * Each bit in the integer corresponds to a node. If a bit is 1, the node is included in the subset; if it's 0, the node is not included.
 * For instance, for a set of 4 nodes (0, 1, 2, 3), the integer 1011 (binary) represents the subset containing nodes 0, 1, and 3 (but not 2).
 * Why Use Integers for Sets
 * Efficiency:
 *
 * Using integers is memory efficient because each integer can represent a subset of nodes using a small amount of space.
 * Operations on integers (bitwise operations) are fast, making it computationally efficient to check membership, iterate through subsets, etc.
 * Easy Subset Generation:
 *
 * By iterating from 0 to (1 << n) - 1, where n is the number of nodes, all possible subsets can be generated. Each value represents a unique subset.
 * For example, with 3 nodes, you would iterate from 000 (binary for 0) to 111 (binary for 7), covering all 8 possible subsets.
 * Example with Explanation
 * Consider n = 3 (nodes 0, 1, 2):
 *
 * The subsets can be represented as:
 * 000 (0 in decimal): No nodes included.
 * 001 (1 in decimal): Only node 0 included.
 * 010 (2 in decimal): Only node 1 included.
 * 011 (3 in decimal): Nodes 0 and 1 included.
 * 100 (4 in decimal): Only node 2 included.
 * 101 (5 in decimal): Nodes 0 and 2 included.
 * 110 (6 in decimal): Nodes 1 and 2 included.
 * 111 (7 in decimal): Nodes 0, 1, and 2 included.
 * In the Context of the Code
 * Iterating Through Subsets:
 *
 * The loop for (int set = 0; set < (1 << n); set++) iterates through all possible subsets of the n nodes.
 * For each value of set, the binary representation indicates which nodes are included in that subset.
 * Checking Membership:
 *
 * The expression (set >> u & 1) == 1 checks if the u-th bit is set to 1, meaning node u is included in the subset.
 * Similarly, (set >> v & 1) == 1 checks if node v is included.
 * Using the Subset:
 *
 * For each subset, the code constructs an adjacency matrix (grid) representing the connections between the included nodes.
 * It then uses the Floyd-Warshall algorithm to compute shortest paths within this subset.
 * Finally, it checks if all pairs of nodes within the subset can be connected within the given maxDistance.
 * Summary
 * Using an integer to represent a set is a compact and efficient method to handle subsets of nodes.
 * Each bit in the integer indicates the presence or absence of a node in the subset,
 * allowing for quick membership checks and easy generation of all possible subsets.
 * This method is particularly useful in combinatorial algorithms where you need to explore all subsets of a given set.
 */
public class Leetcode2959 {

    public static void main(String[] args) {
        System.out.println(1<<4);
        int n = 3, maxDistance = 5;
        int[][] roads = {{0,1,2},{1,2,10},{0,2,10}};

        Leetcode2959 leetcode2959 = new Leetcode2959();
        int res = leetcode2959.numberOfSets(n, maxDistance, roads);


        System.out.println(res);
    }


    private static class Graph {

        private int n;
        private List<int[]>[] g;

        public Graph(int n, int[][] edges) {
            this.n = n;
            g = new ArrayList[n];
            for (int i = 0; i < n; ++i) g[i] = new ArrayList();
            for (int[] e : edges) {
                g[e[0]].add(new int[] {e[1], e[2]});
                g[e[1]].add(new int[] {e[0], e[2]});
            }
        }

        public int shortestPath(int src, int dst, boolean[] f) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(n, (o1, o2) -> o1[0] - o2[0]);
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            boolean[] sptSet = new boolean[n];
            pq.add(new int[] {0, src});
            dist[src] = 0;

            while (!pq.isEmpty()) {
                int from = pq.poll()[1];
//                if (from == dst) break;
                if (!sptSet[from] && g[from] != null) {
                    sptSet[from] = true;
                    for (int[] e : g[from]) {
                        int to = e[0];
                        int cost = e[1];
                        if (!f[to] && (dist[to] > dist[from] + cost)) {
                            dist[to] = dist[from] + cost;
                            pq.add(new int[] {dist[to], to});
                        }
                    }
                }
            }

            return dist[dst];
        }
    }


    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        Graph graph = new Graph(n, roads);
        boolean[] f = new boolean[n];
        int ans = 0;
        for (int i = 0; i < (1<<n); ++i) {
            Arrays.fill(f, false);
            for (int j = 0; j < n; ++j) {
                if (((i >>> j) & 1) == 1) f[j] = true;
            }
            boolean valid = true;
            outer:
            for (int src = 0; src < n; ++src) {
                if (!f[src]) {
                    for (int dst = src + 1; dst < n; ++dst) {
                        if (!f[dst]) {
                            int dist = graph.shortestPath(src, dst, f);
                            if (dist > maxDistance) {
                                valid = false;
                                break outer;
                            }
                        }
                    }
                }
            }
            if (valid) ++ans;
        }
        return ans;
    }

}



