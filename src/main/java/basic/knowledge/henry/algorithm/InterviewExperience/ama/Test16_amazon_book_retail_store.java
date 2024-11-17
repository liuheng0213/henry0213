package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.*;


//amazon book retail store
//arr[i] 表示 第i天上货的书的序。书要依据序来读
public class Test16_amazon_book_retail_store {
    public static void main(String[] args) {
//        int[] arr = {3, 2, 1, 4, 5};
        int[] arr = {2,1,4,3};

        List<Integer> list = new ArrayList<>();

        List<List<Integer>> result = solve(Arrays.asList(1,4,3,2,5),5);

        for (List<Integer> group : result) {
            System.out.println(group);
        }
    }

    public static List<List<Integer>> solution(List<Integer> volumes, int n){
        boolean[] avails = new boolean[n + 1];
        avails[0] = true;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i< volumes.size();i++){
            int volumn = volumes.get(i);
            boolean avail = avails[volumn- 1];
            avails[volumn] = true;
           if(!avail){
               List<Integer> tmp = new ArrayList<>();
               tmp.add(-1);
               ans.add(tmp);
           }else{

           }
        }
        return null;
    }
    public static List<List<Integer>> solve(List<Integer> volumes, int n) {
        // Initialize the availability array and result list
        boolean[] avail = new boolean[n + 1];
        List<List<Integer>> ans = new ArrayList<>();

        // Initially, volume 0 is available
        avail[0] = true;

        for (int idx = 0; idx < n; idx++) {
            int currVol = volumes.get(idx);
            // Mark the current volume as available
            avail[currVol] = true;

            // Check if the previous volume is available
            if (avail[currVol - 1]) {
                // If available, initialize a temporary list and add the current volume
                List<Integer> temp = new ArrayList<>();
                temp.add(currVol);

                // Check subsequent volumes while they are available and within bounds
                while (currVol + 1 <= n && avail[currVol + 1]) {
                    temp.add(currVol + 1);
                    currVol++;
                }

                // Add the temporary list to the result
                ans.add(temp);

            } else {
                // If the previous volume is not available, add a list with -1
                List<Integer> temp = new ArrayList<>();
                temp.add(-1);
                ans.add(temp);
            }
        }

        return ans;
    }


}
