package basic.knowledge.henry.algorithm.InterviewExperience.ama;

//分割 dns 两边都是回型 或者删一个变成回型
public class Test23_1119_online1 {

    private String solution(String dna_sequence) {
        char[] chs = dna_sequence.toCharArray();

        int[][] dp = new int[chs.length][chs.length];
        //dp[i][j] has the values of 0, 1, 2

        for(int j = 1;j< dp[0].length;j++){
            dp[j - 1][j] = chs[j -1]== chs[j] ? 0 : 1;
            for(int i = j - 1;j>=0;j--){
                if(chs[i] == chs[j]){
                    dp[i][j] = dp[i+1][j - 1];
                }else{
                    if(dp[i+1][j] == 0 || dp[i][j - 1] == 0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = 2;
                    }
                }
            }
        }

        for(int i = 0;i< chs.length -1;i++){
            if(dp[0][i]< 2 && dp[i+1][chs[chs.length- 1]]< 2){
                return "YES";
            }
        }

        return "NO";
    }
}
