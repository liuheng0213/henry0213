package basic.knowledge.henry.algorithm.InterviewExperience.ama;

//stamp_tagging_parcels
//stamps 可以重复选
public class Test13_stamp_tagging_parcels {
    public static void main(String[] args) {
        int numberPackaging = new Test13_stamp_tagging_parcels().getNumberPackaging(new int[]{3,1,1});
        System.out.println(numberPackaging);
    }

    public int getNumberPackaging(int[] arr){
        int n = arr.length;

        int[] stamps = new int[5];
        for(int i =0;i< stamps.length;i++){
            stamps[i] =i+1;
        }

        return helper(-1,0,stamps,arr);

    }

    private int helper(int preStamp, int idx, int[] stamps, int[] arr) {
        if(idx == arr.length){
            return 1;
        }

        int res = 0;
        for(int i =0;i< stamps.length;i++){
//            if(stamps[i] == 0){
//                continue;
//            }
           if(preStamp == -1){
               System.out.println("hahaha");
               int cur = stamps[i];
//               stamps[i] = 0;  // 不需要因为stamps 可以重复选
               res+= helper(cur,idx+1,stamps,arr);
//               stamps[i] = cur;
           }else{
               int cur = stamps[i];
//               stamps[i] = 0;
               if(arr[idx] > arr[idx -1] && cur > preStamp){
                   res+=helper(cur,idx+1,stamps,arr);
               }else if(arr[idx] < arr[idx -1] && cur < preStamp){
                   res+=helper(cur,idx+1,stamps,arr);
               }else if(arr[idx] == arr[idx -1] && cur !=preStamp){
                   res+=helper(cur,idx+1,stamps,arr);
               }
//               stamps[i] = cur;
           }
        }
        System.out.println("res " + res);
        return res;
    }
}
