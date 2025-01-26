package basic.knowledge.henry.algorithm.InterviewExperience.At.rankVote;

import java.util.*;

// use stream
public class VoteRank_Stream_Score {
    public static void main(String[] args) {
        VoteRank_Stream_Score voteRankStream = new VoteRank_Stream_Score();
        String[] votes = new String[]{"ABC","BCD","ACD","BCE"};
        voteRankStream.rankTeams(votes);
    }
    HashMap<Character,Integer> nameToScore = new HashMap<>();


    private void rankTeams(String[] votes) {
        for(String v : votes){
            for(int i = 0;i< v.length();i++){
                Character name = v.charAt(i);
                if(i == 0){
                    nameToScore.put(name,nameToScore.getOrDefault(name,0) + 3);
                }else if(i == 1){
                    nameToScore.put(name,nameToScore.getOrDefault(name,0) + 2);
                }else if(i == 2){
                    nameToScore.put(name,nameToScore.getOrDefault(name,0) + 1);
                }
            }
        }


        printRank(4);

    }

    private void printRank(int k) {
        List<Character> keys = new ArrayList<>(nameToScore.keySet());
        Collections.sort(keys,(a,b)->nameToScore.get(b)-nameToScore.get(a));
        for(Character key : keys){
            System.out.println(key + " get score :" + nameToScore.get(key));
            k--;
            if(k == 0){
                return;
            }
        }
    }


}
