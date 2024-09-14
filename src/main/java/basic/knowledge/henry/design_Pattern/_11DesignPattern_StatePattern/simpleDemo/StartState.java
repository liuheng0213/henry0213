package basic.knowledge.henry.design_Pattern._11DesignPattern_StatePattern.simpleDemo;

public class StartState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");

        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }

}
