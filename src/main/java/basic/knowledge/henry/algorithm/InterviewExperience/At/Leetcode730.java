package basic.knowledge.henry.algorithm.InterviewExperience.At;


import java.util.HashMap;
/**
 *       Case 1: c1!=c2 :
 *       dps(c1(m)) + dps((m)c2) - dps(m)
 *
 *       Case 2: c1==c2
 *
 *       Sub-Case 1) a____m____a -> 2*dps(m)+2  : No a in middle part
 *       Sub-Case 2) a____m____a -> 2*dps(m)+1  : Only one a in middle part
 *                        a
 *       Sub-Case 3) a______m_______a -> 2*dps(m)-dps(m') : two a's(left and right nearest)
 *                       a__m'__a
 */

/**
 *        S1    S2      S3        S4
 *       (m)   a(m)    (m)d     a(m)d
 *       _ _   a _ _   _ _ d   a _ _ d
 *       _ b   a _ b   _ b d   a _ b d
 *       _ c   a _ c   _ c d   a _ c d
 *       b c   a b c   b c d   a b c d
 *
 *       we can have four sets at each point.
 *
 *       Now considering the formula
 *       '.' corresponds to intersection
 *       (S1 U S2 U S3 U S4) = S1 + S2 + S3 + S4 -(S1.S2) - (S1.S3) - (S1.S4) - (S2.S3)
 *                             - (S2.S4) - (S3.S4) + (S1.S2.S3) + (S1.S2.S4) + (S2.S3.S4)
 *                             + (S1.S3.S4) - (S1.S2.S3.S4)
 *
 *       Now consider case when
 *       Case 1: c1!=c2 :
 *       dps(c1(m)) + dps((m)c2) - dps(m)
 *       As we can see S4=0 clearly here and (S2.S3)=0 as S1's contains a at start but S2's
 *       does not & vice-versa
 *
 *       The formula get reduced to
 *       = S1 + S2 + S3 - (S1.S2) - (S1.S3)
 *       = S1 + S2 + S3 - (S1.S2) - (S1.S3) + S1 - S1
 *       = S1 + S2 - (S1.S2) + S1 + S3 - (S1.S3) - S1
 *       = (S1 U S2) + (S1 U S3) - S1
 *       = dps(c1(m)) + dps((m)c2) - dps(m)
 *
 *       Similarly we can reduce and derive the formula by observation for other cases also.
 *
 */
public class Leetcode730 {
    public static void main(String[] args) {
        int mod=1000000007;
        System.out.println(mod);
        Leetcode730 leetcode730 = new Leetcode730();
        int res1= leetcode730.countPalindromicSubsequences("bacb");
        int res2= leetcode730.countPalindromicSubsequences_2("bacb");
        System.out.println(res1);
        System.out.println(res2);
        int res = leetcode730.countPalindromicSubsequences("abacba");
        int res3 = leetcode730.countPalindromicSubsequences_2("abacba");
        System.out.println(res);
        System.out.println(res3);
    }


    public int countPalindromicSubsequences_2(String s){
        int n = s.length();
        char[] c = s.toCharArray();
        int[] prev = new int[n];
        int[] next = new int[n];

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i< n;i++){
            if(map.containsKey(c[i])){
                prev[i] = map.get(c[i]);
            }else{
                prev[i] = -1;
            }
            map.put(c[i],i);
        }

        map.clear();

        for(int j = n - 1;j >=0;j--){
            if(map.containsKey(c[j])){
                next[j] = map.get(c[j]);
            }else{
                next[j] = -1;
            }
            map.put(c[j],j);
        }
        int[][] dp = new int[n][n];// dp[i][j] means result from i~j in s
        int mod=(int)1e9+7;

        for(int i = n- 1;i>=0;i--){
            for(int j = i;j<n;j++){
                if(j == i){
                    dp[i][j] = 1;
                }else if(j == i+1){
                    dp[i][j] = 2;
                }else if(c[i] != c[j]){
                    dp[i][j] = ((dp[i][j - 1]%mod + dp[i+1][j]%mod)%mod - dp[i+1][j - 1]%mod +mod)%mod;
                }else {
                    int prej = prev[j];
                    int nexti = next[i];
                    if(i == prej && j == nexti){
                        dp[i][j] = (2 * (dp[i+1][j-1]%mod)) +2;
                    }else if(prej== nexti){
                        dp[i][j] = (2 * (dp[i+1][j-1]%mod)) +1;
                    }else{
                        dp[i][j] =((2*dp[i+1][j-1])%mod - dp[nexti+1][prej-1] + mod)%mod;
                    }

                }
            }
        }


        return dp[0][n - 1];
    }
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        char[] c = s.toCharArray();

        //We need to do some preprocessing for storing the next and previous occurences of
        //each character

        //storing index of previous ooccurence of c[i]
        int[] prev = new int[n];
        //storing index of next ooccurence of c[i]
        int[] next = new int[n];

        HashMap<Character,Integer> map = new HashMap();

        //Preprocessing for previous
        for(int i=0;i<n;i++){
            if(map.containsKey(c[i])) prev[i]=map.get(c[i]);
            else prev[i]=-1;
            map.put(c[i],i);
        }
        map.clear();

        //Preprocessing for next.
        for(int i=n-1;i>=0;i--){
            if(map.containsKey(c[i])) next[i]=map.get(c[i]);
            else next[i]=-1;
            map.put(c[i],i);
        }

        int[][] dp = new int[n][n];
        int mod=(int)1e9+7;
        for(int g=0;g<n;g++){
            for(int i=0,j=g;j<n;i++,j++){
                if(g==0) dp[i][j]=1;
                else if(g==1) dp[i][j]=2;
                else if(c[i]==c[j]) {
                    /**
                     //Finding the nearest index of c[i] to the right of i
                     int li = s.indexOf(c[i],i+1);
                     ////Finding the nearest index of c[i] to the left of j
                     int ri = s.lastIndexOf(c[i],j-1);
                     **/

                    //Use preprocessing here. ***

                    int li = next[i], ri=prev[j];

                    //Sub-Case 1)
                    if(li==j&&ri==i) dp[i][j] = (2*dp[i+1][j-1])%mod+2;
                        //Sub-Case 2)
                    else if(li==ri) dp[i][j] = (2*dp[i+1][j-1])%mod+1;
                        //Sub-Case 3)
                    else dp[i][j] = ((2*dp[i+1][j-1])%mod - dp[li+1][ri-1] + mod)%mod;
                }
                else dp[i][j] =  ((dp[i+1][j]%mod + dp[i][j-1]%mod)%mod -
                            dp[i+1][j-1] + mod)%mod;
            }
        }
        return dp[0][n-1];

    }
}
