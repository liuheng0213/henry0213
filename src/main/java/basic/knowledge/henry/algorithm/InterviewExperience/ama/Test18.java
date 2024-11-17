package basic.knowledge.henry.algorithm.InterviewExperience.ama;


/**
 *  a product identifier string s,
 */
public class Test18 {


    public int maximizeScore(String s){
        int res = s.length();
        char endChar = s.charAt(s.length() - 1);
        char startChar = s.charAt(0);
        int str = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == startChar) {
                str = i;
            } else if (c == endChar) {
                res = Math.min(i - str + 1, res);
            }
        }

        return s.length() - res;
    }
}
