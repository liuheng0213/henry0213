package basic.knowledge.henry.algorithm.InterviewExperience.At.rankVote;

import java.util.*;

// use stream
public class VoteRank_Stream_Score {
    public static void main(String[] args) {
        VoteRank_Stream_Score voteRankStream = new VoteRank_Stream_Score();

        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("B", "A", "C");
        List<String> list3 = Arrays.asList("D", "A", "C");
        List<String> list4 = Arrays.asList("B", "A", "D");
        List<String> list5 = Arrays.asList("B", "A", "D");
        List<String> list6 = Arrays.asList("B", "C", "D");
        List<String> list7 = Arrays.asList("B", "A", "C");
        voteRankStream.rankTeams(list1);
        System.out.println("====================");
        voteRankStream.rankTeams(list2);
        System.out.println("====================");
        voteRankStream.rankTeams(list3);
        System.out.println("====================");
        voteRankStream.rankTeams(list4);
        System.out.println("====================");
        voteRankStream.rankTeams(list5);
        System.out.println("====================");
        voteRankStream.rankTeams(list6);
        System.out.println("====================");
        voteRankStream.rankTeams(list7);
    }
    HashMap<String,Integer> nameToScore = new HashMap<>();

    //use this is not a good idea , because when delete a element in the hashset will use for loop
    HashMap<Integer,LinkedList<String>> freqToStrs = new HashMap<>();

    private void rankTeams(List<String> list) {
        for(int i =0;i< list.size();i++){
            String name = list.get(i);
            if(i == 0){
                nameToScore.put(name,nameToScore.getOrDefault(name,0) + 3);
            }else if(i == 1){
                nameToScore.put(name,nameToScore.getOrDefault(name,0) + 2);
            }else{
                nameToScore.put(name,nameToScore.getOrDefault(name,0) + 1);
            }
        }

        printRank(4);

    }

    private void printRank(int k) {
        List<String> keys = new ArrayList<>(nameToScore.keySet());
        Collections.sort(keys,(a,b)->nameToScore.get(b)-nameToScore.get(a));
        for(String key : keys){
            System.out.println(key);
            k--;
            if(k == 0){
                return;
            }
        }
    }


}
