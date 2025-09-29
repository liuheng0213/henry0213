package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k0;

public class SolutionCorrect {
    public static void main(String[] args) {
        String[] words = new String[]{"jbaby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        String note1 = "bbbblkkjbaby";
        String res = new SolutionCorrect().solution(words,note1);
        System.out.println(res);
    }

    private String solution(String[] words, String note1) {
        char[] chs = new char[26];

        for(char ch : note1.toCharArray()){
            chs[ch- 'a']++;
        }

        for(String w : words){
            boolean valid = validate(chs,w);
            if(valid){
                return w;
            }
        }
        return "";
    }

    private boolean validate(char[] chs, String w) {
        char[] tmp = new char[26];

        for(char c: w.toCharArray()){
            tmp[c- 'a']++;
        }
        for(int i =0;i< 26;i++){
            if(tmp[i] > chs[i]){
                return false;
            }
        }
        return true;
    }


}
