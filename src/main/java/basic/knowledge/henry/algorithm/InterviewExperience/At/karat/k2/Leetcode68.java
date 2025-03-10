package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.k2;

import java.util.ArrayList;
import java.util.List;

public class Leetcode68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> cur = new ArrayList<>();
        List<String> res = new ArrayList<>();
        int num_letters = 0;
        for(String word : words){
            // System.out.println(word);
            if(word.length() + num_letters+ cur.size() > maxWidth){
                for(int i = 0;i< maxWidth - num_letters;i++){
                    cur.set(i % (cur.size() - 1 >0 ?cur.size() - 1 : 1 ),cur.get(i % (cur.size() - 1 >0 ?cur.size() - 1 : 1)) + " ");
                }
                StringBuilder sb = new StringBuilder();
                for(String s : cur){
                    sb.append(s);
                }
                cur.clear();
                res.add(sb.toString());
                num_letters = 0;
            }

            cur.add(word);
            num_letters += word.length();
        }

        // for(String w : cur){
        //      System.out.print(w);
        // }


        int n = words.length;
        StringBuilder lastLine = new StringBuilder();
        for(int i =0;i< cur.size();i++){
            if(i != cur.size() -1){
                lastLine.append(cur.get(i)).append(" ");
            }else{
                lastLine.append(cur.get(i));
            }
        }

        System.out.print(lastLine);
        for(int i = lastLine.length();i< maxWidth;i++){
            lastLine.append(" ");
        }

        res.add(lastLine.toString());

        return res;
    }
}
