package basic.knowledge.henry.algorithm.InterviewExperience.gs;

public class Leetcode443 {
    public static void main(String[] args) {
        String str = "aabbccc";
        int compress = new Leetcode443().compress(str.toCharArray());
        System.out.println(compress);
    }

    public int compress(char[] chars) {
        int num = 1 ;
        char pre = chars[0];

        StringBuilder sb = new StringBuilder(pre);
        sb.append(pre);
        // System.out.println(sb.length());
        for(int i = 1;i< chars.length;i++){
            if(chars[i] == pre){
                num++;
            }else if(chars[i] != pre){
                pre = chars[i];
                if(num != 1){
                    sb.append(num);
                    // System.out.println("num1 " + num);
                }
                sb.append(chars[i]);
                num = 1;

            }

            if(i == chars.length - 1){
                if(num != 1){
                    // System.out.println("num2 " + num);
                    sb.append(num);
                }
            }
        }
        System.out.println("sb  " + sb.toString());
        return sb.length();
    }
}
