package basic.knowledge.henry.algorithm.InterviewExperience.microsoft;

public class Task2 {
    public static void main(String[] args) {
        new Task2().solution("abc","abcdef");
    }

    private void solution(String str1, String str2) {
        int i = 0;
        int j = 0;

        while(j< str2.length()){
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(j);

            if(ch1 == ch2){
                i++;
                j++;
            }else{

            }
        }
    }
}
