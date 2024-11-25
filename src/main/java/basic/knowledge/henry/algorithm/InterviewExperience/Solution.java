package basic.knowledge.henry.algorithm.InterviewExperience;

import java.util.*;


class Solution {
    public static void main(String[] args) {
        Solution obj = new Solution();
        boolean res = obj.isSolvable(new String[]{"SEND","MORE"},"MONEY");
        System.out.println(res);
    }


    public boolean isSolvable(String[] words, String result) {
        List<Character> chs = new ArrayList<>();
        boolean[] added = new boolean[26];
        for(String str : words){
            for(int i = 0;i< str.length();i++){
                char ch = str.charAt(i);
                if(!added[ch - 'A']){
                    chs.add(ch);
                    added[ch - 'A'] = true;;
                }
            }
        }

        for(int i = 0;i< result.length();i++){
            char ch = result.charAt(i);
            if(!added[ch - 'A']){
                chs.add(ch);
                added[ch - 'A'] = true;;
            }
        }

        return helper(chs,0,new HashMap<>(),new boolean[10],words,result);
    }
    private int convert(HashMap<Character,Integer> chToNum,String s){
        int num = 0;
        for(int i = 0;i< s.length();i++){
            num = num * 10 + chToNum.get(s.charAt(i));
        }
        return num;
    }

    private boolean isValid(HashMap<Character,Integer> chToNum,String[] words,String result){
        int sum = 0;

        for(String w : words){
            sum += convert(chToNum,w);
        }

        return sum == convert(chToNum,result);
    }

    private boolean helper(List<Character> chs,int idx,HashMap<Character,Integer> chToNum,boolean[] used,String[] words,String result){
        if(idx == chs.size()){
            if(isValid(chToNum,words,result)){
                return true;
            }else{
                return false;
            }
        }

        char ch = chs.get(idx);
        boolean flag = false;
        for(int i =0;i<=9;i++){
            boolean valid = false;
            if(i == 0){
                for(String w : words){
                    if(w.charAt(0)== ch && w.length()>1){
                        valid = false;
                        break;
                    }
                }

                if(result.charAt(0)== ch && result.length()>1){
                    valid = false;
                }
            }



            if(!valid){
                continue;
            }

            if(used[i]){
                continue;
            }
            chToNum.put(ch,i);
            used[i] = true;

            // flag = flag || helper(chs,idx+1,chToNum,used,words,result);
            if(helper(chs,idx+1,chToNum,used,words,result)){
                return true;
            }
            chToNum.put(ch,-1);
            used[i] = false;
        }

        return false;
    }
}

