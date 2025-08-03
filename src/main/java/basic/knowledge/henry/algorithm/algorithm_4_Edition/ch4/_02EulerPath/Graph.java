package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath;

import java.util.List;

public interface Graph {
    void addEdge(int v, int w);
    List<Integer> neighbors(int v);
    int size();
}