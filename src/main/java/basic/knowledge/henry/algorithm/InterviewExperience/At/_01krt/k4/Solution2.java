package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k4;

import java.util.*;

/**
 *
 * You work in an automated robot factory. Once robots are assembled, they are sent to the shipping center via a series of autonomous delivery carts, each of which moves packages on a one-way route.
 *
 * Given input that provides the (directed) steps that each cart takes as pairs, write a function that identifies all the start locations, and a collection of all of the possible ending locations for each start location.
 *
 * In this diagram, starting locations are at the top and destinations are at the bottom - i.e. the graph is directed exclusively downward.
 *
 * A E J Key: [Origins]
 * / \ / \ \
 * B C F L M [Destinations]
 * \ / \ /
 * K G
 * /
 * H I
 * paths = [
 * ["B", "K"],
 * ["C", "K"],
 * ["E", "L"],
 * ["F", "G"],
 * ["J", "M"],
 * ["E", "F"],
 * ["C", "G"],
 * ["A", "B"],
 * ["A", "C"],
 * ["G", "H"],
 * ["G", "I"]
 * ]
 *
 * Expected output (unordered):
 * [
 * "A": ["K", "H", "I"],
 * "E": ["H", "L", "I"],
 * "J": ["M"]
 * ]
 *
 *
 */
public class Solution2 {

    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("B", "K"));
        paths.add(Arrays.asList("C", "K"));
        paths.add(Arrays.asList("E", "L"));
        paths.add(Arrays.asList("F", "G"));
        paths.add(Arrays.asList("J", "M"));
        paths.add(Arrays.asList("E", "F"));
        paths.add(Arrays.asList("C", "G"));
        paths.add(Arrays.asList("A", "B"));
        paths.add(Arrays.asList("A", "C"));
        paths.add(Arrays.asList("G", "H"));
        paths.add(Arrays.asList("G", "I"));
        HashMap<String, HashSet<String>> solution = new Solution2().solution(paths);
        System.out.println(solution);

    }

    public HashMap<String, HashSet<String>> solution(List<List<String>> paths){
        HashMap<String, List<String>> grapth = new HashMap<>();
        HashSet<String> starts = new HashSet<>();
        HashSet<String> seconds = new HashSet<>();

        for(List<String> path : paths){
            String start = path.get(0);
            String end = path.get(1);
            starts.add(start);
            starts.add(end);
            seconds.add(end);
            grapth.computeIfAbsent(start,k->new ArrayList<>());
            grapth.get(start).add(end);
        }
        starts.removeAll(seconds);
        HashMap<String, HashSet<String>> res = new HashMap<>();
//        System.out.println(starts);
        for(String start : starts){
            res.put(start,new HashSet<>());
            dfs(start,start,grapth,res);
        }
        return res;
    }

    private void dfs(String start, String cur,HashMap<String, List<String>> grapth, HashMap<String, HashSet<String>> res) {
        if(!grapth.containsKey(cur) ){
            res.get(start).add(cur);
            return;
        }

        for(String next : grapth.get(cur)){
            dfs(start,next,grapth,res);
        }
    }
}
