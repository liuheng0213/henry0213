package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._06Portfolio;



import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PortfoliosTest {
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
        PortfoliosTest portfoliosTest = new PortfoliosTest();
        System.out.println(portfoliosTest.split(1000, p4));
        System.out.println(portfoliosTest.split(1000, p3));
        System.out.println(portfoliosTest.split(1000, p2));
        System.out.println(portfoliosTest.split(1000, p1));
        System.out.println(portfoliosTest.split(1000, p0));
    }

    public Map<String,Double> split(double sum, Portfolio portfolio) {
        Map<String,Double> stocknameToAmount = new HashMap<>();
        for(Portfolio subPortfolio: portfolio.getPortfolios().keySet()){
            Double portion = portfolio.getPortfolios().get(subPortfolio);
            Map<String,Double> substocknameToAmount = split(sum * portion,subPortfolio);
            for(String stockName : substocknameToAmount.keySet()){
                double value = stocknameToAmount.getOrDefault(stockName,0.0);
                Double exsitingValue = substocknameToAmount.get(stockName);
                stocknameToAmount.put(stockName,value + exsitingValue);
            }
        }

        for(Stock stock : portfolio.getStocks().keySet()){
            Double portion = portfolio.getStocks().get(stock);
            double value = portion * sum;
            double exsitingValue = stocknameToAmount.getOrDefault(stock.getName(),0.0);
            stocknameToAmount.put(stock.getName(),exsitingValue + value);
        }

        return stocknameToAmount;
    }




}
