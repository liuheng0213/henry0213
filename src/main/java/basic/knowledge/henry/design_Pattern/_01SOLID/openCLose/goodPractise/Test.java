package basic.knowledge.henry.design_Pattern._01SOLID.openCLose.goodPractise;

public class Test {
    public static void main(String[] args) {
        PayHandler payHandler = new PayHandler();
        payHandler.register("a",new AlipayProcessor());
        payHandler.register("w",new WechatProcessor());
        payHandler.register("p",new PaypalProcessor());


        payHandler.pay("w");
    }
}
