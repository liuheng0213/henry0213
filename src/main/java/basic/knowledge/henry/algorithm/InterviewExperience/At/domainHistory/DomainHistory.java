package basic.knowledge.henry.algorithm.InterviewExperience.At.domainHistory;

import java.util.*;

public class DomainHistory {
    public static void main(String[] args) {
        DomainHistory domainHistory = new DomainHistory();
        String[] A = new String[]{"3234.html", "xys.html", "7hsaa.html","haha","www.com","yyy.com","zzz.com"};
        String[] B = new String[]{"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html","shah","www.com","yyy.com","zzz.com"};
        String[] lcs = domainHistory.LCS(A, B);
        System.out.println(Arrays.toString(lcs));


       List<String> completedId = Arrays.asList("3123122444", "234111110", "8321125440", "99911063");
       List<String> adClicks = Arrays.asList("122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
               "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens","122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
               "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets","92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
               "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens");
       List<String> allUser = Arrays.asList("2339985511,122.121.0.155", "234111110,122.121.0.1","3123122444,92.130.6.145",
               "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000","8321125440,82.1.106.8", "99911063,92.130.6.144");
        List<String> res = domainHistory.adConversion(completedId, adClicks, allUser);

        System.out.println(res);

    }
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> count = new HashMap();
        for (String cd : cpdomains) {
            int i = cd.indexOf(' ');
            int n = Integer.valueOf(cd.substring(0, i));
            String s = cd.substring(i + 1);
            for (i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    count.put(d, count.getOrDefault(d, 0) + n);
                }
            }
            count.put(s, count.getOrDefault(s, 0) + n);
        }

        List<String> res = new ArrayList();
        for (String d : count.keySet()) res.add(count.get(d) + " " + d);
        return res;
    }

    public static String[] LCSBetter(String[] A, String[] B) {
        int maxLength = 0;
        String[] longestSubstr = new String[0];

        int[] status = new int[A.length + 1];

        for (int i = 0; i < B.length; i++) {
            int[] newStatus = new int[A.length + 1];

            for (int j = 0; j < A.length; j++) {
                if (A[j].equals(B[i])) {
                    int newLength = status[j] + 1;

                    if (newLength > maxLength) {
                        maxLength = newLength;
                        longestSubstr = Arrays.copyOfRange(A, j - newLength + 1, j + 1);
                    }
                    newStatus[j + 1] = newLength;
                }
            }
            status = newStatus;
        }
        return longestSubstr;
    }


    /**
     * user0 = [ "/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html" ]
     * user2 = [ "/nine.html", "/two.html", "/three.html", "/four.html", "/six.html",
     * "/seven.html" ]
     * print(sol.LCS(user2, user0))   should be "/four.html", "/six.html"
     * @param A
     * @param B
     * @return
     */
    public String[] LCS(String[] A, String[] B) {
        int m = A.length;
        int n = B.length;
        //dp[i][j] means the len of common string of subs arr  0~i from  A and sub arr 0~j from B
        int[][] dp = new int[m][n];
        int max = 0;
        int endIdx = -1;
        dp[0][0] = A[0].equals(B[0]) ? 1 : 0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i].equals(B[j])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        endIdx = i;
                    }
                }
            }
        }

        System.out.println("max " + max);
        System.out.println("endIdx " + endIdx);
        String[] lstHistory = Arrays.copyOfRange(A, endIdx - max +1, endIdx + 1);

        return lstHistory;
    }


    /**
     *inputs: a list of user ids + IPs, a list of user ids who have made purchases, a list of advertisement
     * clicks with user IPs.
     * Each user id has at most 1 IP.
     * Output: for each ad, output the number of clicks and the number of purchases.
     *
     *
     * completedId = ["3123122444", "234111110", "8321125440", "99911063"]
     * adClicks = ["122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
     *  "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
     *  "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
     *  "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
     *  "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
     *  "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens"]
     * allUser = ["2339985511,122.121.0.155", "234111110,122.121.0.1",
     *  "3123122444,92.130.6.145",
     * "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
     *  "8321125440,82.1.106.8", "99911063,92.130.6.144"]
     * print(sol.ad_conversion(completedId, adClicks, allUser))
     *
     * click 并不代表买了
     * @param userIDs
     * @param adClicks
     * @param userIPs
     * @return
     */
    public List<String> adConversion(List<String> userIDs, List<String> adClicks, List<String> userIPs) {
        Set<String> userIDSet = new HashSet<>(userIDs);
        Map<String, List<String>> adText = new HashMap<>();

        for (String click : adClicks) {
            String[] parsed = click.split(",");
            adText.putIfAbsent(parsed[2], new ArrayList<>());
            adText.get(parsed[2]).add(parsed[0]);
        }

        Map<String, String> ipUser = new HashMap<>();
        for (String ip : userIPs) {
            String[] parsed = ip.split(",");
            ipUser.put(parsed[1], parsed[0]);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : adText.entrySet()) {
            String text = entry.getKey();
            List<String> clicks = entry.getValue();
            int buyer = 0;

            for (String ip : clicks) {
                if (ipUser.containsKey(ip) && userIDSet.contains(ipUser.get(ip))) {
                    buyer++;
                }
            }
            res.add(buyer + " of " + clicks.size() + " " + text);
        }

        return res;
    }

}
