package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.directed;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.Graph;
import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.undirected.UndirectedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 对于有向图：
 *
 * 如果所有节点的入度和出度都相等，那么起点和终点是同一个节点，
 * 存在欧拉回路。我们可以以任意一个节点作为起点，遍历所有边后，一定可以回到起点。
 *
 * 如果存在两个节点入度和出度不相等，那么起点和终点分别是这两个节点，
 * 存在欧拉路径。我们可以选一个入度和出度不相等的切入度小于出度的节点作为起点，遍历所有边后，一定可以到达另一个入度和出度不相等的节点。
 *
 *
 */
public class DirectedHierholzerAlgorithm {
    public static void main(String[] args) {
        Graph graph = new DirectedGraph(3);
        graph.addEdge(0,1);
        graph.addEdge(1,2);


        List<Integer> eulerianPath = findEulerianPath(graph);
        System.out.println(eulerianPath);
    }

    private static List<Integer> findEulerianPath(Graph graph) {
        int start = findStartNode_directed(graph);

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


    // 有向图的 findStartNode 函数实现
    private static int findStartNode_directed(Graph graph) {
        // 记录每个节点的入度和出度
        int[] inDegree = new int[graph.size()];
        int[] outDegree = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            for (Integer to : graph.neighbors(i)) {
                inDegree[to]++;
                outDegree[i]++;
            }
        }
        // 如果每个节点的入度出度都相同，则存在欧拉回路，任意节点都可以作为起点
        boolean allSame = true;
        for (int i = 0; i < graph.size(); i++) {
            if (inDegree[i] != outDegree[i]) {
                allSame = false;
                break;
            }
        }
        if (allSame) {
            // 任意节点都可以作为起点，就让我们以 0 作为起点吧
            return 0;
        }

        // 现在寻找是否存在节点 x 和 y 满足：
        // inDegree[x] - outDegree[x] = 1 && inDegree[y] - outDegree[y] = -1
        // 且其他节点的入度和出度都相等
        // 如果存在，则 x 是起点，y 是终点
        int x = -1, y = -1;
        for (int i = 0; i < graph.size(); i++) {
            int delta = inDegree[i] - outDegree[i];
            if (delta == 0) {
                continue;
            }
            if (delta != 1 && delta != -1) {
                // 不存在欧拉路径
                return -1;
            }
            if (delta == -1 && x == -1) {
                x = i;
            } else if (delta == 1 && y == -1) {
                y = i;
            } else {
                // 不存在欧拉路径
                return -1;
            }
        }

        if (x != -1 && y != -1) {
            // 存在欧拉路径，x 是起点
            return x;
        }

        return -1;
    }
}
