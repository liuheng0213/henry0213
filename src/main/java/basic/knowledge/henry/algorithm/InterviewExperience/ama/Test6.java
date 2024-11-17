package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//getMaDiscountPairs
//find pairs of power of 3
public class Test6 {
    public static void main(String[] args) {
        int maDiscountPairs = new Test6().getMaDiscountPairs(new int[]{2, 1, 7,25});
        System.out.println(maDiscountPairs);
    }
    public int getMaDiscountPairs(int[] arr){
        HashMap<Integer, List<Integer>> valToCount = new HashMap<>();
        int res = 0;
        for(int i = 0;i< arr.length;i++){
            for(int key: valToCount.keySet()){
                if(isPowerOf3(key+ arr[i])){
                    res+= valToCount.get(key).size();
                }
            }
            valToCount.putIfAbsent(arr[i],new ArrayList<>());
            valToCount.get(arr[i]).add(i);
        }

        return res;
    }

    private boolean isPowerOf3(int num) {
        while(num >= 3){
            if(num % 3 != 0){
                return false;
            }
            num /= 3;
        }
        return num == 1;
    }
}
