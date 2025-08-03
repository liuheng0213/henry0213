package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.undirected;


import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * 对于无向图：
 *
 * 如果所有节点的度数都是偶数，那么起点和终点是同一个节点，存在欧拉回路。我们可以以任意一个节点作为起点，遍历所有边后，一定可以回到起点。
 *
 * 如果存在两个奇数度节点，那么起点和终点分别是这两个节点，存在欧拉路径。我们可以任选一个奇数度节点作为起点，遍历所有边后，一定可以到达另一个奇数度节点。
 *
 *
 */

public class UndirectedHierholzerAlgorithm {

    public static void main(String[] args) {
        Graph graph = new UndirectedGraph(3);
        graph.addEdge(0,1);
        graph.addEdge(1,2);


        List<Integer> eulerianPath = findEulerianPath(graph);
        System.out.println(eulerianPath);
    }

    public static List<Integer> findEulerianPath(Graph graph) {
        int start = findStartNode_undirected(graph);

        if (start == -1) {
            // 不存在欧拉路径/回路
            return null;
        }

        // 2. 从起点开始执行 DFS 算法，记录后序遍历结果
        List<Integer> postOrder = new ArrayList<>();
        traverse(graph, start, postOrder);

        // 3. 反转后序遍历结果，即可得到欧拉路径/回路
        Collections.reverse(postOrder);
        return postOrder;
    }

    private static void traverse(Graph graph, int cur, List<Integer> postOrder) {
        // base case
        if (cur < 0 || cur >= graph.size()) {
            return;
        }

        while (!graph.neighbors(cur).isEmpty()) {
            Integer next = graph.neighbors(cur).get(0);
            // 直接删掉边，避免重复遍历
            graph.neighbors(cur).remove(next);
            traverse(graph, next, postOrder);
        }

        // 后序位置，记录后序遍历结果
        postOrder.add(cur);
    }

    private static int findStartNode_undirected(Graph graph) {
        int start = 0;
        // 记录奇数度节点的数量
        int oddDegreeCount = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (graph.neighbors(i).size() % 2 == 1) {
                oddDegreeCount++;
                start = i;
            }
        }
        // 如果奇数度节点的数量不是 0 或 2，则不存在欧拉路径
        if (oddDegreeCount != 0 && oddDegreeCount != 2) {
            return -1;
        }
        // 如果奇数度节点的数量是 0，则任意节点都可以作为起点，此时 start=0
        // 如果奇数度节点的数量是 2，任意一个奇数度节点作为起点，此时 start 就是奇数度节点
        return start;
    }



}
