package basic.knowledge.henry.algorithm.InterviewExperience.At.portfolioHenry;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Portfolio p4 = new Portfolio("P4");
        Portfolio p2 = new Portfolio("P2");
        Portfolio p3 = new Portfolio("P3");
        Portfolio p1 = new Portfolio("P1");
        Portfolio p0 = new Portfolio("P0");
        p4.add(new Stock("OKTA"),0.6);
        p4.add(new Stock("TEAM"),0.4);


        p3.add(new Stock("EA"), 0.2);
        p3.add(new Stock("ATVI"), 0.8);
//        p3.add(new Portfolio("HENRY"), 0.8);


        p2.add(p3, 0.4);
        p2.add(p4, 0.4);
        p2.add(new Stock("APPL"), 0.2);


        p1.add(p2, 0.4);
        p1.add(new Stock("APPL"), 0.2);
        p1.add(new Stock("EA"), 0.4);

        p0.add(p1,0.2);
        p0.add(p2,0.4);
        p0.add(p3,0.4);

        System.out.println(split(1000, p4));
        System.out.println(split(1000, p3));
        System.out.println(split(1000, p2));
        System.out.println(split(1000, p1));
        System.out.println(split(1000, p0));
    }

    /**
     * 拿到最终的stock mapping sum的结果
     * @param sum
     * @param p
     * @return
     */
    private static Map<String,Double> split(double sum, Portfolio p) {
        Map<String,Double> nameToNum = new HashMap<>();

        if(!p.portfolios.isEmpty()){
            Map<Portfolio, Double> portfolioToPortion = p.portfolios;
            for(Portfolio portfolio : portfolioToPortion.keySet()) {
                double portion = portfolioToPortion.get(portfolio);
                Map<String, Double> split = split(sum * portion, portfolio);//绝对是 name mapping num
                for(String subKey: split.keySet()){
                    nameToNum.put(subKey,nameToNum.getOrDefault(subKey,0.0) + split.get(subKey));
                }
            }
        }

        if(!p.stocks.isEmpty()){
            Map<Stock, Double> stockToPortion = p.stocks;
            for(Stock stock : stockToPortion.keySet()) {
                double portion = stockToPortion.get(stock);
                //不能单纯的put 还要相加 val
                nameToNum.put(stock.name,nameToNum.getOrDefault(stock.name,0.0) + portion * sum);
            }
        }

        return nameToNum;
    }
}
