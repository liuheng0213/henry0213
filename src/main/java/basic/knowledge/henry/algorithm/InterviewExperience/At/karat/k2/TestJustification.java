package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.k2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJustification {
    public static void main(String[] args) {
        List<String> res =  new TestJustification().solution(Arrays.asList("The day began as still as the", "night abruptly lighted with", "brilliant flame"),12);
        System.out.println(res);
    }

    private List<String> solution(List<String> asList,int max) {
        List<String> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        int num_of_letters = 0;
        for(String str : asList){
            String[] strs = str.split(" ");
            for(String w : strs){
                if(cur.size() + num_of_letters + w.length() > max){
                    for(int i = 0;i< cur.size(); i++){
                        if(i != cur.size() - 1){
                            cur.set(i,cur.get(i) + "-");
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    for (String s : cur) sb.append(s);
                    res.add(sb.toString());
                    cur.clear();
                    num_of_letters = 0;
                }
                cur.add(w);
                num_of_letters += w.length();
            }
        }

        StringBuilder lastLine = new StringBuilder();
        for (int i = 0; i < cur.size(); i++) {
            lastLine.append(cur.get(i));
            if (i != cur.size() - 1) lastLine.append(" ");
        }
//        while (lastLine.length() < max) lastLine.append(" ");
        res.add(lastLine.toString());

        return res;

    }
}
