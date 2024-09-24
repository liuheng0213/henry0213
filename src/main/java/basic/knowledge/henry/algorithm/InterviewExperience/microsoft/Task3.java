package basic.knowledge.henry.algorithm.InterviewExperience.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        String[] arr = new String[]{"toast", "bread", "breada", "chees"};
        String S = "abcdeeghrs";
        List<String> solution = new Task3().solution(arr, S);
        System.out.println(solution);

    }

    public List<String> solution(String[] arr,String S){
        int[] count = new int[26];

        for(int i = 0;i< S.length();i++){
            count[S.charAt(i)-'a']++;
        }


        int[] tmp;
        List<String> res = new ArrayList<>();
        for(int i = 0;i< arr.length;i++){
            String str = arr[i];
            tmp = Arrays.copyOf(count,count.length);

            boolean flag = true;
            for(int j = 0;j< str.length();j++){
                tmp[str.charAt(j)-'a']--;
                if(tmp[str.charAt(j)-'a'] < 0){
                    flag = false;
                    break;
                }
            }

            if(flag){
                res.add(str);
            }

        }

        return res;
    }
}
