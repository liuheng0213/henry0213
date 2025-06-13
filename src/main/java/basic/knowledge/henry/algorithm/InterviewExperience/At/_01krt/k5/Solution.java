package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k5;

import java.util.*;

public class Solution {

    /**
     * users : [["Alice", "Connect"], ["Bob", "Disconnect"], ["John", "Connect"]]
     * Goal: Group users by their action.
     *
     * Expected Output:
     * {
     *     "Connect": ["Alice", "John"],
     *     "Disconnect": ["Bob"]
     * }
     *
     *
     * @return
     */
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("Alice", "Connect"));
        list.add(Arrays.asList("Bob", "Disconnect"));
        list.add(Arrays.asList("John", "Connect"));
        HashMap<String,HashSet<String>> res = new Solution().solution(list);
        System.out.println(res);
    }
    public HashMap<String,HashSet<String>> solution(List<List<String>> list){
        HashMap<String,HashSet<String>> res = new HashMap<>();
        for(List<String> sub : list){
            String key = sub.get(1);
            String val = sub.get(0);
            res.computeIfAbsent(key,k->new HashSet<>());
            res.get(key).add(val);
        }
        return res;
    }
}
