package basic.knowledge.henry.design_Pattern._03DesignPattern_Singleton;

public class SingletonUsage implements Runnable {
    @Override
    public void run() {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}
