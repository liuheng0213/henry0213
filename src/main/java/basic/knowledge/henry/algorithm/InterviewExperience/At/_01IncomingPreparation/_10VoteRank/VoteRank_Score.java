package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._10VoteRank;

import java.util.*;

/**
 * follow up  * 1st Strategy -> supposed 2 candidates (A, B) have same points.
 * We have to declare the candidate as winner whoever reaches the winning point 1st.
 */
// use stream
public class VoteRank_Score {
    public static void main(String[] args) {
        VoteRank_Score voteRankStream = new VoteRank_Score();
        String[] votes = new String[]{"ABC","BCD","ACD","BCE","AEC"};
        voteRankStream.rankTeams(votes);
    }
    HashMap<Character,Integer> nameToScore = new HashMap<>();
    HashMap<Integer,LinkedList<Character>> score2Chs = new HashMap<>();

    private void rankTeams(String[] votes) {
        for(String v : votes){
            for(int i = 0;i< v.length();i++){
                Character name = v.charAt(i);
                int old = nameToScore.getOrDefault(name,0);
                int latest = 0;
                if(i == 0){
                    latest = old+ 3;
                    nameToScore.put(name,latest);
                }else if(i == 1){
                    latest = old+ 2;
                    nameToScore.put(name,latest);
                }else {
                    latest = old+ 1;
                    nameToScore.put(name,latest);
                }
                score2Chs.putIfAbsent(old,new LinkedList<>());
                LinkedList<Character> chs = score2Chs.get(old);
                chs.remove(name);
                if(chs.isEmpty()){
                    score2Chs.remove(old);
                }
                score2Chs.putIfAbsent(latest,new LinkedList<>());
                score2Chs.get(latest).add(name);
            }
        }


        List<Character> rank = rank();
        System.out.println(rank);
    }

    private List<Character> rank() {
        List<Integer> scores = new ArrayList<>(score2Chs.keySet());
        Collections.sort(scores,(a,b)->b-a);
        List<Character> res = new ArrayList<>();
        for(int score : scores){
            LinkedList<Character> characters = score2Chs.get(score);
            for(char ch : characters){
                res.add(ch);
            }
        }

        return res;
    }


}
