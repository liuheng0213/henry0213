package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**

 *      //一定要定义一个 array，这样可以增强读的性能 且不要用封装的double ,否则会有nullpointer exception
 *   主要用的computeIfAbsent
 */
public class CostExplorerTwice {
    public static void main(String[] args) throws ParseException {
        CostExplorerTwice costExplorerTwice = new CostExplorerTwice();
        int month1 = costExplorerTwice.getMonth("2013-12-31");
        System.out.println(month1);
        int month2 = costExplorerTwice.getMonth("2013-01-31");
        System.out.println(month2);


    }
    Map<String, Map<String,double[]>> customer2produce2MonthlyCost = new HashMap<>();
    double[] monthlyCost = new double[12];
    public void subscribe(SubscriptionInformation subscriptionInformation) throws ParseException {
        String customer = subscriptionInformation.getCustomerID();
        Map<String, double[]> produce2MonthlyCost = customer2produce2MonthlyCost.computeIfAbsent(customer, k -> new HashMap<>());
        String producetName = subscriptionInformation.getProduct().getName();
        String date = subscriptionInformation.getProduct().getSubscription().getDate();
        String planId = subscriptionInformation.getProduct().getSubscription().getPlanId();
        
        
        if(!validate(planId)){
            throw new IllegalArgumentException("incoorect input");
        }
        int month = getMonth(date);

        double[] doubles = produce2MonthlyCost.computeIfAbsent(producetName, k -> new double[12]);
        for(int i = month;i< 12;i++){
            SubscriptionEnum subscriptionEnum = SubscriptionEnum.valueOf(planId);
            monthlyCost[i] += subscriptionEnum.getValue() - doubles[i];
            doubles[i] = subscriptionEnum.getValue();
        }



    }

    public double[] getMonthlyCost(){
        return monthlyCost;
    }

    public double getAnnualCost(){
        double res = 0d;
        for(int i =0;i< 12;i++){
            res+= monthlyCost[i];
        }
        return res;
    }



    private boolean validate(String planId) {
        SubscriptionEnum[] values = SubscriptionEnum.values();
        for(SubscriptionEnum subEnum : values){
            if(subEnum.name().equals(planId)){
                return true;
            }
        }

        return false;
    }


    public int getMonth(String datestr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(datestr);
        int month = date.getMonth();
        return month;
    }

}
