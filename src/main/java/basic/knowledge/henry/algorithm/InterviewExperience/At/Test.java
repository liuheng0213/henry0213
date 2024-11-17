package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Test {
    public static void main(String[] args) {
        int i = new Test().maximumQualitySum(new int[]{1, 2, 3, 4, 5}, 2);
        System.out.println(i);
    }
    public int maximumQualitySum(int[] packets, int channels) {
        // write your code here
        int n = packets.length;
        double res = 0.0;
        int i = n -1;
        while(i >=0){
            res+= packets[i];
            i--;
            channels--;
            if(channels == 1 && i >= 0){
                break;
            }
        }
        double md = 0.0;
        if((i+1)% 2 == 0){
            md = (packets[i/2] + packets[i/2 +1])/2.0;
        }else{
            md = packets[i/2];
        }

        res += md;
        return (int) Math.ceil(res);
    }
}
