package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import java.util.Arrays;

//* -1 后  看presum 是不是全部大于0
public class Test23_1119_online2 {
    int[] dp;
    public static void main(String[] args) {
        int res = new Test23_1119_online2().solution(new int[]{1,1,1,1,1,1});
        System.out.println(res);
    }

    int res = Integer.MIN_VALUE;
    private int solution(int[] arr) {
        dp = new int[arr.length];
        Arrays.fill(dp,Integer.MIN_VALUE);
        int[] presum = new int[arr.length];
        presum[0] = arr[0];
        for(int i =1;i< arr.length;i++){
            presum[i] = arr[i]+ presum[i-1];
        }
        helper(presum,arr,arr.length -1,0);
        return res;
    }

    private void helper(int[] presum, int[] arr, int idx, int num) {
        if(idx == -1){
            return;
        }

        boolean flag = false;
        for(int k = idx;k< arr.length;k++){
            presum[k] -= 2*arr[k];
            if(presum[k] <= 0){
                flag = false;
                //不需要下面的循环， 如果flag = false 就return 了
//                for(int j = idx;j<=k;j++) {
//                    presum[j] += 2*arr[j];
//                }
                return;
            }
        }

        flag = true;
        num++;
        res = Math.max(res,num);

        for(int i = idx- 1;i>=-1;i--){
            helper(presum,arr,i,num);
        }

        if(flag){
            for(int k = idx;k< arr.length;k++){
                presum[k] += 2*arr[k];
            }
            num--;
        }
        flag = false;
    }
}
