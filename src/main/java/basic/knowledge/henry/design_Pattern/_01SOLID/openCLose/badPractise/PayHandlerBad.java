package basic.knowledge.henry.design_Pattern._01SOLID.openCLose.badPractise;

public class PayHandlerBad {
    public static void main(String[] args) {
        new PayHandlerBad().pay("Alipay");
    }
    public void pay(String type){
        if(type.equals("Alipay")){
            System.out.println("I am using Alipay");
        }else if(type.equals("Wechatpay")){
            System.out.println("I am using Wechatpay");
        }else if(type.equals("paypal")){
            System.out.println("I am using paypal");
        }
    }
}
