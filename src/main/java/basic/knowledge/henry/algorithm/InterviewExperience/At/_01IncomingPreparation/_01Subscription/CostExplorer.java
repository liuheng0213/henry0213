package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._01Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  follow up was basically to include trial period for 3 months where cost would be $0.
 *  And update monthly and yearly projection appropriately.
 *
 *  clarification for followup , if a customer send a new subscription information at the end of the trial period ,will the trial period extended
 */
public class CostExplorer {

    HashMap<String,Double> types = new HashMap<>();
    {
        types.put("BASIC",5.0);
        types.put("STANDARD",10.0);
        types.put("PREMIUM",15.0);
    };
    HashMap<String, Map<String,double[]>> customer2product2CostList = new HashMap<>();

    //上一次的更新得记下来， 或者同一个customer 同一个product 只能用一次trial
    HashMap<String, Map<String,Integer>> customer2product2TrialMonths = new HashMap<>();
    public void subscribe(SubscriptionInformation subscriptionInformation) throws ParseException {
        Map<String, double[]> product2CostList = customer2product2CostList.getOrDefault(subscriptionInformation.customerID,new HashMap<>());

        String name = subscriptionInformation.getProduct().getName();
        String datestr = subscriptionInformation.getProduct().getSubscription().getDate();
        String planId = subscriptionInformation.getProduct().getSubscription().getPlanId();
        Double fee = types.get(planId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(datestr);
        int month = date.getMonth();
        double[] doubles = product2CostList.getOrDefault(name,new double[12]);

        int trial = 0;
        for(int i = month;i < 12;i++){
            if(trial > 2){
                doubles[i] = fee;
            }
            trial++;
        }
        product2CostList.put(name,doubles);

        customer2product2CostList.put(subscriptionInformation.customerID,product2CostList);

    }

    public double[] monthlyCostList(){
        double[] monthlyCost = new double[12];
        for(String cusId : customer2product2CostList.keySet()){
            Map<String, double[]> product2CostList = customer2product2CostList.get(cusId);

            for(String product : product2CostList.keySet()){
                double[] monthly = product2CostList.getOrDefault(product,new double[12]);
                for(int i = 0;i< 12;i++){
                    monthlyCost[i] +=  monthly[i];
                }
            }
        }

        return monthlyCost;
    }

    public double annualCost(){
        double[] monthlyCost = monthlyCostList();
        double totalCost = 0.0d;
        for(double c : monthlyCost){
            totalCost += c;
        }

        return totalCost;
    }

}
