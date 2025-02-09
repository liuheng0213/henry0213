package basic.knowledge.henry.design_Pattern._06DesignPattern_Strategy.annotation;

@PriceRegion(min=3000)
public class GoldVip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.7;
    }
}