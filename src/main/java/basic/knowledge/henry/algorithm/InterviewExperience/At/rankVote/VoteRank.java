package basic.knowledge.henry.algorithm.InterviewExperience.At.rankVote;

import java.util.*;

// use stream
public class VoteRank {
    public static void main(String[] args) {
        String[] votes = new String[]{"ABC","BCD","ACD","BCE"};

        VoteRank voteRank = new VoteRank();
        String s = voteRank.rankTeams(votes);
        System.out.println(s);
    }
    public String rankTeams(String[] votes) {

        HashMap<Character,int[]> voteMap = new HashMap<>();
        for(String str : votes){
            char[] chs = str.toCharArray();
            for(int i = 0;i< chs.length;i++){
                int[] cur = voteMap.getOrDefault(chs[i],new int[str.length()]);
                cur[i]++;
                voteMap.put(chs[i],cur);
            }
        }
//        List<Character> voteMasterList = new ArrayList<>(voteMap.keySet());
//        int len = votes[0].length();
//        Collections.sort(voteMasterList,(a, b)->{
//            int index = 0;
//            while(index < len && voteMap.get(a)[index] == voteMap.get(b)[index]){
//                index++;
//            }
//            if(index == len){
//                return a - b;
//            }else{
//                return  voteMap.get(b)[index] - voteMap.get(a)[index];
//            }
//        });
//        StringBuilder answer = new StringBuilder();
//        for(Character key: voteMasterList) {
//            answer.append(key);
//        }
//        return answer.toString();

        ArrayList<Map.Entry<Character, int[]>> entries = new ArrayList<>(voteMap.entrySet());
        int len = votes[0].length();
        Collections.sort(entries,(a, b)->{
            int index = 0;
            while(index < len && a.getValue()[index] == b.getValue()[index]){
                index++;
            }
            if(index == len){
                return a.getKey() - b.getKey();
            }else{
                return  b.getValue()[index] - a.getValue()[index];
            }
        });

        StringBuilder answer = new StringBuilder();
        for(Map.Entry<Character, int[]> entry: entries) {
            answer.append(entry.getKey());
        }
        return answer.toString();

    }
}
