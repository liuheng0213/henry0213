package basic.knowledge.henry.algorithm.InterviewExperience.ama;
//amazon academy mean rank count
public class Test11_mean_rank_count {

    public static void main(String[] args) {
        System.out.println(1%1);
        int[] meanRankCount = new Test11_mean_rank_count().getMeanRankCount(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println(meanRankCount);
    }
    public int[] getMeanRankCount(int[] rank, int n){


        int[] presum = new int[n+1];
        presum[0] = 0;
        for(int i =1;i< n+1;i++){
            presum[i] = presum[i-1] + rank[i-1];
        }

        int[] res = new int[n];

        for(int i = 0;i< n+1;i++){
            for(int j = i+1;j< n+1;j++){
                int len =j-i;
                System.out.println("len " + len);
                int sum = presum[j] - presum[i];
                System.out.println("sum "+ sum);
                if( sum%len == 0){
                    int mean = sum/len;
                    System.out.println("mean " + mean);
                    res[mean - 1]++;
                    System.out.println("len "+ len + "..." + res[mean - 1]);
                }


            }
        }
        return res;
    }
}
