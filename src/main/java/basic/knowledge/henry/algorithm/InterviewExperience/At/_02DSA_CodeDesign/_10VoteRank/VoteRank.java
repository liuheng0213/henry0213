package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._10VoteRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * The solution was pretty simple (can check solutions on leetcode). However the interviewer asked follow up questions on this which was the tricky part.
 *
 * How to handle the scenario where the candidates have the same points? What strategies can be used.
 * He proposed 2 strategies and I was supposed to implement both of them based on the input.
 * 1st Strategy -> supposed 2 candidates (A, B) have same points. We have to declare the candidate as winner whoever reaches the winning point 1st.
 * 2nd strategy -> votes placed in the ballot. Whoever has the maxium votes in ballot at 0th index then 1st index and then last index.
 *
 * For strategy 1 -> I implemented a doubly linked list having each node as set. Basically I was trying to implement the ALL O(1) problem's solution. With help of this I can return the 0th index on the list's node.
 * For strategy 2 -> I tried maintaing an array of points at each index for each candidate. EG - A: [0,0,0]
 *
 *
 *
 *
 * the following is the strategy 2
 */
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
        //如果这题也要区分tie 创建一个
        //HashMap<String,LinkedList<Character>> sequence2List 记录0_3 在index0上有3分
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
