package basic.knowledge.henry.algorithm.InterviewExperience.ms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task4 {
    public static void main(String[] args) {
        List<String> solution = new Task4().solution(6,2,4,7);
        System.out.println(solution);

    }

    //00:00
    //23:59
    private List<String> solution(int a, int b, int c, int d) {
        int[] arr = new int[]{a,b,c,d};
        Set<String> res = new HashSet<>();
       for(int i = 0;i< arr.length;i++){
           int num1 = arr[i];
           if(num1 == 0 || num1 == 1 || num1 == 2){
               for(int j = 0; j< arr.length;j++){
                   if(j == i){
                       continue;
                   }
                   int num2 = arr[j];
                   if(num1 == 2 && num2 > 3){
                       continue;
                   }

                   for(int k = 0; k< arr.length;k++){
                       if(k == i || k == j ){
                           continue;
                       }
                       int num3 = arr[k];
                       if(num3> 5){
                           continue;
                       }
                        for(int m = 0; m<arr.length;m++){
                            if(m == i || m == j || m == k){
                                continue;
                            }
                            int num4 = arr[m];
                            String str= "";
                            str+=num1;
                            str+=num2;
                            str+=":";
                            str+=num3;
                            str+=num4;
                            res.add(str);

                        }

                   }
               }
           }
       }
        return new ArrayList<>(res);



    }


}
