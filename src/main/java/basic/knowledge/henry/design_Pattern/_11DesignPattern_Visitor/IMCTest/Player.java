package basic.knowledge.henry.design_Pattern._11DesignPattern_Visitor.IMCTest;


public abstract class Player {
    String name;


    public Player(String name) {
        this.name = name;
    }

    public abstract HandSymbol accept(Visitor visitor);
}
