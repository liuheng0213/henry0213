package basic.knowledge.henry.algorithm.InterviewExperience.At.portfolioHenry;


import java.util.*;

public class Portfolio {
    final Map<Portfolio,Double> portfolios;
    final Map<Stock,Double> stocks;
    double portion;

    String name;

    public Portfolio(String name) {
        this.name = name;
        portfolios = new HashMap<>();
        stocks = new HashMap<>();
        this.portion = 0;
    }
    public void add(Portfolio portfolio,double fraction){
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
