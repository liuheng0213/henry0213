package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import java.util.*;

public class VoteRank {
    public static void main(String[] args) {
        String[] votes = new String[]{"ABC","BCD","ACD","BCE"};

        VoteRank voteRank = new VoteRank();
        String s = voteRank.rankTeams(votes);
        System.out.println(s);
    }

    private String rankTeams(String[] votes) {
        HashMap<Character,int[]> voteToRank = new HashMap<>();
        for(String str : votes){
            for(int i =0;i< str.length();i++){
                char vote = str.charAt(i);
                voteToRank.putIfAbsent(vote,new int[3]);
                voteToRank.get(vote)[i]++;
            }
        }

        // sort according to value
        Set<Map.Entry<Character, int[]>> entries = voteToRank.entrySet();
        List<Character>  voteList = new ArrayList<>(voteToRank.keySet());
        Collections.sort(voteList,(a,b)->{
            int[] va = voteToRank.get(a);
            int[] vb = voteToRank.get(b);
            int idx = 0;
            while(idx < va.length && va[idx] == vb[idx]){
                idx++;
            }
            if(idx< va.length){
                return vb[idx] - va[idx];
            }else{
                return a - b;
            }
        });


        StringBuilder sb = new StringBuilder();
        for(Character ch : voteList){
            sb.append(ch);
        }

        return sb.toString();
    }
}
