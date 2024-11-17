package basic.knowledge.henry.algorithm.InterviewExperience.rk;

public class StringSolution {
    public static void main(String[] args) {
        StringSolution stringSolution = new StringSolution();
        boolean res= stringSolution.solution("r*kte","rokte");
        System.out.println(res);

        boolean res1= stringSolution.solution2("r3r5q","roktretyuiq");
        System.out.println(res1);
    }

    private boolean solution2(String s, String p) {
        int i = 0;
        int num = 0;
        int j =0;
        while(i < s.length()){
            if(j > p.length() - 1){
                return false;
            }
            char ch_s = s.charAt(i);
            char ch_p = p.charAt(j);
            if(Character.isDigit(ch_s)){
                num = num * 10 + (ch_s - '0');
                i++;
            }else if(num != 0){
                j += num;
                num = 0;
            }else{
                if(ch_s == ch_p){
                    i++;
                    j++;
                }else{
                    return false;
                }
            }
        }

        return j == p.length();
    }

    private boolean solution(String s, String p) {
        int i = 0;
        while(i < s.length()){
            if(i > p.length() - 1){
                return false;
            }
            char ch_s = s.charAt(i);
            char ch_p = p.charAt(i);
            if(ch_s == '*' || ch_s == ch_p){
                i++;
            }else{
                return false;
            }
        }

        return i == p.length();
    }
}
