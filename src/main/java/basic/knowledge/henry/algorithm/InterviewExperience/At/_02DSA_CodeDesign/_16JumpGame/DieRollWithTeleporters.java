package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._16JumpGame;


/**
 * Given a board with numbers from 0 to lastNumber, startPosition,
 * teleporters like ("3,1", "5,10", "8,2") and maxValue of a die.
 * Also, if after rolling the die, a number greater than lastNumber is reached then teleport to the lastNumber.
 * Find all final positions which can be reached by rolling the die once (1 to maxValue). (Working solution of this one).
 *
 *
 * Modified first problem, given teleporters, lastNumber, maxValue of die and startPosition,
 * is it possible to reach the lastNumber by rolling the die any number of times.
 * (Only solution approach was required without code).
 */
import java.util.*;

public class DieRollWithTeleporters {

    public Set<Integer> getFinalPositions(
            int lastNumber,
            int startPosition,
            List<int[]> teleporters,
            int maxDieValue
    ) {
        HashMap<Integer,List<Integer>> graph = createGraph(teleporters);
        HashSet<Integer> possiblePositions = new HashSet<>();;
        dfs(startPosition,lastNumber,maxDieValue,graph,possiblePositions);
        return possiblePositions;
    }

    private void dfs(int position, int lastNumber, int maxDieValue, HashMap<Integer, List<Integer>> graph,HashSet<Integer> possiblePositions) {
        possiblePositions.add(position);
        for(int die = 1;die<=maxDieValue;die++ ){
            if(graph.containsKey(die)){
                for(Integer nextPo: graph.get(die)){
                    if(possiblePositions.contains(nextPo)){
                        continue;
                    }
                    if(nextPo > lastNumber){
                        nextPo = lastNumber;
                    }
                    dfs(nextPo,lastNumber,maxDieValue,graph,possiblePositions);
                }
            }
        }
    }

    private static HashMap<Integer, List<Integer>> createGraph(List<int[]> teleporters) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0;i< teleporters.size();i++){
            int start = teleporters.get(i)[0];
            int end = teleporters.get(i)[1];

            List<Integer> endLists = graph.computeIfAbsent(start, k -> new ArrayList<>());
            endLists.add(end);

            List<Integer> startLists = graph.computeIfAbsent(end, k -> new ArrayList<>());
            startLists.add(start);
        }

        return graph;
    }

    // Example usage
    public static void main(String[] args) {
        int lastNumber = 10;
        int startPosition = 2;
        List<int[]> teleporters = Arrays.asList(new int[]{3,1}, new int[]{5,10}, new int[]{8,2});
        int maxDieValue = 6;

        Set<Integer> finalPositions = new DieRollWithTeleporters().getFinalPositions(lastNumber, startPosition, teleporters, maxDieValue);
        System.out.println("Reachable positions after one roll: " + finalPositions);
    }
}
