package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode1220 {

    int mod = 1000000007;
    int[][] dp;

    public int countVowelPermutation(int n) {
       dp = new int[n+1][26];
       return (helper(n - 1,'a')+helper(n - 1,'e')+helper(n - 1,'i')+helper(n - 1,'o')+helper(n - 1,'u'))%mod;

    }

    private int helper(int i, char ch) {
        if(i == 0){
            return 1;
        }

        if(dp[i][ch-'a']!= 0){
            return dp[i][ch-'a'];
        }


        int count = 0;

        if(ch == 'a'){
            count = helper(i-1, 'e')%mod;
        }else if(ch == 'e'){
            count = (helper(i-1, 'a')%mod +  helper(i-1, 'i')%mod)%mod;
        }else if(ch == 'i'){
            count = (helper(i-1, 'a')%mod +  helper(i-1, 'e')%mod+  helper(i-1, 'o')%mod+  helper(i-1, 'u')%mod)%mod;
        }else if(ch == 'o'){
            count = (helper(i-1, 'a')%mod +  helper(i-1, 'e')%mod+  helper(i-1, 'o')%mod)%mod;
        }else{
            count = helper(i-1, 'a')%mod;
        }

        dp[i][ch-'a'] = count;
        return count;
    }
}
