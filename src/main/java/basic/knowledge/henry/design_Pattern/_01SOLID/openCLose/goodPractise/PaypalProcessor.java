package basic.knowledge.henry.design_Pattern._01SOLID.openCLose.goodPractise;

public class PaypalProcessor implements PayProcessor{
    @Override
    public void handle() {
        System.out.println("I am using paypal");
    }
}
