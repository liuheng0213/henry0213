package basic.knowledge.henry.algorithm.InterviewExperience.At.portfolioHenry;

import basic.knowledge.henry.algorithm.InterviewExperience.At.PortfolioTest;

import java.util.*;

public class Portfolio extends Stock{
    final Map<Stock,Double> portfolios;
    double portion;


    public Portfolio(String name,double fraction) {
        super(name);
        this.portion = fraction;
        this.portfolios = new HashMap<>();
    }




    public void add(Stock stock,double fraction){
        if(portion + fraction <= 1){
            this.portfolios.put(stock,fraction);
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
