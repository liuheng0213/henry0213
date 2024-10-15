package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode14 {


    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1){
            return strs[0];
        }

        for(int idx = 0;idx< 200;idx++){
            for(int i = 1;i< strs.length;i++){
                String prev = strs[i - 1];
                String cur = strs[i];
                if(idx > prev.length() - 1 || idx > cur.length() - 1 || prev.charAt(idx) != cur.charAt(idx)){
                    return prev.substring(0,idx);
                }
            }
        }
        return "";
    }
}
