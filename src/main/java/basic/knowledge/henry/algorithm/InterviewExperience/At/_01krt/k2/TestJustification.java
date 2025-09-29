package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJustification {
    public static void main(String[] args) {
        List<String> res =  new TestJustification().solution(Arrays.asList("Hello", "Sir", "Please", "Upvote", "If", "You", "Like", "My", "Postttttttttttt"),10);
        System.out.println(res);//"Hello-Sir", "Please", "Upvote-If", "You-Like", "My-Post"
        List<String> res2 =  new TestJustification().solution2(Arrays.asList("Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"),20);
        System.out.println(res2);
    }


    private List<String> solution(List<String> asList,int max){
        List<String> res = new ArrayList<>();
        String cur = asList.get(0);
        for(int i = 1;i< asList.size();i++){
            if(cur.length() + asList.get(i).length() + 1 > max){
                res.add(cur);
                cur = asList.get(i);
            }else{
                cur = cur+ "-"+ asList.get(i);
            }
        }

        res.add(cur);
//        System.out.println(cur);
        return res;
    }
    private List<String> solution2(List<String> asList,int max) {
        List<String> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        int num_of_letters = 0;
        for(String str : asList) {
            if (cur.size() + num_of_letters + str.length() > max) {
                for (int i = 0; i < max - num_of_letters; i++) {
                    cur.set(cur.size() - 1 == 0 ? 0 : i % (cur.size() - 1), cur.get(cur.size() - 1 == 0 ? 0 : i % (cur.size() - 1)) + " ");
                }
                StringBuilder sb = new StringBuilder();
                for (String s : cur) sb.append(s);
                res.add(sb.toString());
                cur.clear();
                num_of_letters = 0;

                cur.add(str);
                num_of_letters += str.length();
            } else {
                cur.add(str);
                num_of_letters += str.length();
            }
        }


        StringBuilder lastLine = new StringBuilder();
        for (int i = 0; i < cur.size(); i++) {
            if(i != cur.size() - 1){
                lastLine.append(cur.get(i) + " ");
            }else{
                lastLine.append(cur.get(i));
            }
        }
        while (lastLine.length() < max) lastLine.append(" ");
        res.add(lastLine.toString());

        return res;

    }
}
