package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.HashMap;
import java.util.Map;

public class PortfolioTest {
    private static class StockFraction {
        String stock;
        double fraction;

        public StockFraction(String stock, double fraction) {
            this.stock = stock;
            this.fraction = fraction;
        }
    }

    private static class Portfolio {
        private String stock;
        private Double fraction;
        private Map<Portfolio, Double> portfolios;

        public Portfolio() {
            this.portfolios = new HashMap<>();
        }

        private Portfolio(String stock, double fraction) {
            this.stock = stock;
            this.fraction = fraction;
        }

        public void add(String stock, double fraction) {
            this.add(new Portfolio(stock, fraction), 1.0);
        }

        public void add(Portfolio portfolio, double fraction) {
            this.portfolios.put(portfolio, fraction);
        }

        public boolean isStockFraction() {
            return stock != null && fraction != null;
        }

        public StockFraction getStockFraction() {
            if (!isStockFraction()) {
                throw new RuntimeException("not a single stock!");
            }
            return new StockFraction(stock, fraction);
        }

        public Map<Portfolio, Double> getPortfolios() {
            return this.portfolios;
        }
    }


    /**
     * {TEAM=400.0, OKTA=600.0}
     * {ATVI=800.0, EA=200.0}
     * {APPL=400.0, ATVI=320.0, TEAM=160.0, EA=80.0, OKTA=240.0}
     * {APPL=360.0, ATVI=128.0, TEAM=64.0, EA=432.0, OKTA=96.0}
     * @param args
     */
    public static void main(String[] args) {
        Portfolio p4 = new Portfolio();
        p4.add("OKTA", 0.6);
        p4.add("TEAM", 0.4);

        Portfolio p3 = new Portfolio();
        p3.add("EA", 0.2);
        p3.add("ATVI", 0.8);

        Portfolio p2 = new Portfolio();
        p2.add(p3, 0.4);
        p2.add(p4, 0.4);
        p2.add("APPL", 0.4);

        Portfolio p1 = new Portfolio();
        p1.add(p2, 0.4);
        p1.add("APPL", 0.2);
        p1.add("EA", 0.4);

        System.out.println(splits(1000, p4));
        System.out.println(splits(1000, p3));
        System.out.println(splits(1000, p2));
        System.out.println(splits(1000, p1));
    }

    public static Map<String, Double> splits(double amount, Portfolio portfolio) {
        Map<String, Double> map = new HashMap<>();
        for (Map.Entry<Portfolio, Double> portfolioFraction : portfolio.getPortfolios().entrySet()) {
            if (portfolioFraction.getKey().isStockFraction()) {
                StockFraction stockFraction = portfolioFraction.getKey().getStockFraction();
                map.put(stockFraction.stock, map.getOrDefault(stockFraction.stock, 0.0) + stockFraction.fraction * amount);
            } else {
                Map<String, Double> mapPrev = splits(amount * portfolioFraction.getValue(), portfolioFraction.getKey());
                for (String stock : mapPrev.keySet()) {
                    map.put(stock, map.getOrDefault(stock, 0.0) + mapPrev.get(stock));
                }
            }
        }

        return map;
    }
}
