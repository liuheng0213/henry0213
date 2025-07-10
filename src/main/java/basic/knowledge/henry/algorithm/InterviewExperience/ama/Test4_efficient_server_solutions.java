package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Arrays;


/**
 * AWS provides fast and efficient_server_solutions.
 * The developers want to stress-test the quality of the servers' channels.
 * They must ensure the following:
 *
 * Each of the packets must be sent via a single channel.
 * Each of the channels must transfer at least one packet.
 * The quality of the transfer for a channel is
 * defined by the median of the sizes of all the data packets sent through that channel.
 */
public class Test4_efficient_server_solutions {
    public int maximumQualitySum(int[] packets, int channels) {
        Arrays.sort(packets);
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
        return (int)Math.ceil(res);
    }
}
