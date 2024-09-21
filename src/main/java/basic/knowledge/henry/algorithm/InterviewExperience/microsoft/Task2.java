package basic.knowledge.henry.algorithm.InterviewExperience.microsoft;

public class Task2 {
    public static void main(String[] args) {
//        String res = new Task2().solution("2a11[bc]5[dc]7r");
        int res = new Task2().shipWithinDays(new int[]{1,2,3,1,1},4);
        System.out.println(res);
    }
    public int shipWithinDays(int[] weights, int days) {
        int left = 1;
        int right = 0;
        for(int i =0;i< weights.length;i++){
            right+=weights[i];
        }
        int target = -1;
        while(left<=right){
            int mid = (left+ right)>>1;
            int tmpDays=f(weights,mid);
            if(tmpDays== days){
                target= mid;
                right = mid -1;
            }else if(tmpDays > days){//mid is too less
                left = mid+1;
            }else{
                right = mid - 1;
            }
        }

        return target == -1? left : target;
    }

    public int f(int[] weights,int capacity){
        int days = 0;
        int tmp = 0;
        for (int i = 0; i < weights.length;i++ ) {
            if(weights[i]>capacity){
               return Integer.MAX_VALUE;
            }
            tmp+=weights[i];
//            if(weights[i] > mid){
//                break;
//            }
            if(tmp > capacity){
                days++;
                tmp = weights[i];
            }
        }
        days++;
        return days;
    }

    private String solution(String s) {
        String res = "";
        int num = 0;

        char[] chs = s.toCharArray();
        String inbracket = null;
        for(int i = 0;i< chs.length;i++){
           char ch = chs[i];
           if(Character.isDigit(ch)){
               num = num * 10 + (ch - '0');
           }else if(Character.isLetter(ch)){
               if(inbracket == null){
                   for(int i1 = 0;i1< num;i1++){
                       res+=ch;
                   }
                   num = 0;
               }else{
                   inbracket+=ch;
               }

           }else if(ch == '['){
               inbracket = "";
           }else{
               for(int i1 = 0;i1< num;i1++){
                   res+=inbracket;
               }
               num = 0;
               inbracket= null;
           }
        }

        return res;
    }
}
