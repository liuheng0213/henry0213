package basic.knowledge.henry.design_Pattern._00DesignPattern_Singleton;

/**
 * 有双重检查的多线程使用,这是单利的标准写法
 * 支持多线程调用,线程安全
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}