package basic.knowledge.henry.algorithm.InterviewExperience.CBA;


public class Test {
    int n =0;
    public static void main(String[] args) {
        int res = new Test().solution("13471","59604");
        System.out.println(res);
    }

    private int solution(String s, String t) {
        n = s.length();
        StringBuilder sb_s = new StringBuilder(s);
        StringBuilder sb_t = new StringBuilder(t);

        int res = 0;
        for(int i =0;i< n - 1;i++){
            char ch1_s = sb_s.charAt(i);
            char ch2_s = sb_s.charAt(i + 1);
            char ch1_t = sb_t.charAt(i);
            char ch2_t = sb_t.charAt(i + 1);
            int times = isValid(ch1_s,ch2_s,ch1_t,ch2_t);
            if(times > 0){
                int num1 = ch1_s - '0';
                num1 = (num1 + times)%10;
                int num2 = ch2_s - '0';
                num2 = (num2 + times)%10;
                sb_s.setCharAt(i,(char)(num1 + '0'));
                sb_s.setCharAt(i+1,(char)(num2 + '0'));
                res+=times;
            }
        }
        return sb_s.toString().equals(sb_t.toString()) ? res : -1;
    }

    private int isValid(char ch1S, char ch2S, char ch1T, char ch2T) {
        int times = 0;
        int numS = (ch1S - '0') * 10 + (ch2S - '0');
        int numT =0;
        if(ch1T == '0'){
            numT = 100;
        }
        numT += (ch1T - '0') * 10 + (ch2T - '0');
        if(numS > numT){
            return -1;
        }

        while(times * 11 + numS <= numT){
            times++;
        }

        return times - 1;

    }
}
