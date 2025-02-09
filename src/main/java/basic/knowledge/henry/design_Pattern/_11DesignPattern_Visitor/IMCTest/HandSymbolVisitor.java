package basic.knowledge.henry.design_Pattern._11DesignPattern_Visitor.IMCTest;


public class HandSymbolVisitor implements Visitor {

    @Override
    public HandSymbol visit(CmpPlayer cmpPlayer) {
        HandSymbol handSymbol = cmpPlayer.showHandSymbol();
        System.out.println(cmpPlayer.name + " is showing " + handSymbol.getName());
        return handSymbol;
    }

    @Override
    public HandSymbol visit(HumanPlayer humanPlayer) {
        HandSymbol handSymbol = humanPlayer.showHandSymbol();
        System.out.println(humanPlayer.name + " is showing " + handSymbol.getName());
        return handSymbol;
    }
}
