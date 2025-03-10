package basic.knowledge.henry.algorithm.InterviewExperience.At.courseRelated;

import java.util.LinkedList;
import java.util.List;

/**
 * why visited is so important and imporve the performance
 *
 * 哪里有冗余计算呢？我举个例子你就明白了。
 *
 * 假设现在你以节点 2 为起点遍历所有可达的路径，最终发现没有环。
 *
 * 假设另一个节点 5 有一条指向 2 的边，你在以 5 为起点遍历所有可达的路径时，
 * 肯定还会走到 2，那么请问，此时你是否还需要继续遍历 2 的所有可达路径呢？
 * 3-》4-》5-》3 这个环 如果成立，
 * 1-》4-》5—》3-》4 比然还是个环。 4 的visit[4] = true  但onpath的要设为false ; 因为3 要去其他的next node
 * 4 不需要遍历立了
 */
public class Leetcode207DFS {
    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{3,0},{2,3},{4,3},{6,4},{5,4},{3,5},{1,3}};
        int numCourses = 7;
        boolean b = new Leetcode207DFS().canFinish(numCourses, prerequisites);
        System.out.println(b);
    }

    // 记录递归堆栈中的节点
    boolean[] onPath;

    // 记录节点是否被遍历过
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }
        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    // 图遍历函数，遍历所有路径
    void traverse(List<Integer>[] graph, int s) {
        if (hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }

        if (onPath[s]) { // 此时s 有可能为visited if hasCycle
            // s 已经在递归路径上，说明成环了
            hasCycle = true;
            return;
        }

        //这个判断一定要在上面判断的下面
        if (visited[s]) {
            // 不用再重复遍历已遍历过的节点
            return;
        }
        // 前序代码位置
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序代码位置
        onPath[s] = false;
    }

    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 添加一条从 from 指向 to 的有向边
            // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }
        return graph;
    }
}
