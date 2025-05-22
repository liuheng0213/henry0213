package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;



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

        System.out.println(split(1000, p4));
        System.out.println(split(1000, p3));
        System.out.println(split(1000, p2));
        System.out.println(split(1000, p1));
        System.out.println(split(1000, p0));
    }

    private static Map<String,Double> split(double sum, Portfolio portfolio) {
        Map<String,Double> stocknameToAmount = new HashMap<>();
        for(Portfolio subPortfolio: portfolio.portfolios.keySet()){
            Double portion = portfolio.portfolios.get(subPortfolio);
            Map<String,Double> substocknameToAmount = split(sum * portion,subPortfolio);
            for(String stockName : substocknameToAmount.keySet()){
                double value = stocknameToAmount.getOrDefault(stockName,0.0);
                Double exsitingValue = substocknameToAmount.get(stockName);
                stocknameToAmount.put(stockName,value + exsitingValue);
            }
        }

        for(Stock stock : portfolio.stocks.keySet()){
            double value = portfolio.stocks.get(stock) * sum;
            double exsitingValue = stocknameToAmount.getOrDefault(stock.name,0.0);
            stocknameToAmount.put(stock.name,exsitingValue + value);
        }

        return stocknameToAmount;
    }


    static class Stock {
        final private String name;

        public Stock(String name) {
            this.name = name;
        }
    }


   static  class Portfolio {
        final private Map<Portfolio,Double> portfolios;
        final private Map<Stock,Double> stocks;
        private double portion;

        final  private String name;

        public Portfolio(String name) {
            this.name = name;
            portfolios = new HashMap<>();
            stocks = new HashMap<>();
            this.portion = 0;
        }
        public void add(Portfolio portfolio, double fraction){
            if(portion + fraction <= 1){
                this.portfolios.put(portfolio,fraction);
                this.portion+=fraction;
            }else{
                throw new RuntimeException("fraction not correct");
            }
        }

        public void add(Stock stock,double fraction){
            if(portion + fraction <= 1){
                this.stocks.put(stock,fraction);
                this.portion+=fraction;
            }else{
                throw new RuntimeException("fraction not correct");
            }

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Portfolio portfolio = (Portfolio) o;
            return Objects.equals(name, portfolio.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}
