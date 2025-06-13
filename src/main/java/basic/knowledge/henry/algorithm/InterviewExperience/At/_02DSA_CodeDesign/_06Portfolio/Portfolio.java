package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._06Portfolio;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Portfolio {
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

    public void add(Stock stock, double fraction){
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

    public Map<Portfolio, Double> getPortfolios() {
        return portfolios;
    }

    public Map<Stock, Double> getStocks() {
        return stocks;
    }

    public double getPortion() {
        return portion;
    }

    public String getName() {
        return name;
    }
}
