package basic.knowledge.henry.design_Pattern._11DesignPattern_Visitor.IMC;

public class Player {
    Operation operation;
    String name;

    public Player(Operation operation, String name) {
        this.operation = operation;
        this.name = name;
    }

    public HandSymbol play() {
        HandSymbol handSymbol = this.operation.showHandSymbol();
        System.out.println(this.name + " is showing " + handSymbol.getName());
        return handSymbol;
    }

}
