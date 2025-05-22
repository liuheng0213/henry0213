package basic.knowledge.henry.algorithm.InterviewExperience.At.commodityPrice;

import java.util.*;

public class ProductSubscription {
    Map<String, Integer> vip2Amount = createVIP();

    HashMap<String, HashMap<String,int[]>> subscription = new HashMap<>();

    int maxMonth = 12;
    private static Map<String, Integer> createVIP() {
        HashMap<String, Integer>
                table = new HashMap<>();

        table.put("A", 10);
        table.put("B", 20);
        table.put("C", 30);
        return Collections.unmodifiableMap(table);
    }

    public static void main(String[] args) {
        ProductSubscription obj = new ProductSubscription();
        obj.solution("henry","jira",3,"A");
        obj.solution("henry","jira",6,"B");
        obj.solution("henry","confluence",9,"C");
        obj.solution("heng","confluence",2,"A");


        int income = obj.getallIncome("henry");
        System.out.println(income);
    }

    private int getallIncome(String user) {
        HashMap<String, int[]> product2calendar = subscription.get(user);
        List<int[]> cals = new ArrayList<>(product2calendar.values());
        int res = 0;
        for(int[] cal : cals){
            for(int count: cal){
                res+= count;
            }
        }

        return res;
    }

    private void solution(String user, String product, int month, String vipType) {
        HashMap<String, int[]> product2calendar = subscription.computeIfAbsent(user, k->new HashMap<>());
        int[] calendar = product2calendar.getOrDefault(product, new int[12]);
        product2calendar.put(product,calendar);
        Arrays.fill(calendar,month,maxMonth,vip2Amount.get(vipType));
//        System.out.println();
    }


}
