package basic.knowledge.henry.ThreadRelevant._28VolatileAndAtomic.volatile_Usage;


/**
 * https://www.51cto.com/article/747781.html
 * 当子线程修改flag值为false后，主线程的while循环并未停止，说明主线程并没有发现flag值被另外的线程修改
 *
 * 分析：
 *
 * 基于Java8使用的HotSpot虚拟机实现来说，静态变量 flag 存储于方法区中，被所有线程共享
 * 当线程1启动时需要使用flag变量就会将其拷贝进线程1的工作内存，并且修改值为false
 * 主线程使用该变量也是拷贝进自己的工作内存，当拷贝进去时flag变量值都为true，线程1睡眠3秒之后修改了值，并将值刷新进主内存
 * 但是此时主线程循环使用的flag值并不是主内存中最新的，而是线程启动时就拷贝进来的值，所以循环并没有停止，也就是主线程并没有发现值被修改了，因为他没有去获取最新的值。
 * 想要解决这个问题有两种方案
 *
 * 加锁 add volatile in this example
 * 保障变量修改后被其他线程可见
 *
 *
 *
 * 对于可见性，Java提供了volatile关键字来保证可见性。
 *
 * 当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值。
 *
 * 而普通的共享变量不能保证可见性，因为普通共享变量被修改之后，什么时候被写入主存是不确定的，当其他线程去读取时，此时内存中可能还是原来的旧值，因此无法保证可见性。
 *
 * 另外，通过synchronized和Lock也能够保证可见性，
 * synchronized和Lock能保证同一时刻只有一个线程获取锁然后执行同步代码，并且在释放锁之前会将对变量的修改刷新到主存当中。因此可以保证可见性。
 */
public class VolatileMain {
    // 运行标记
    private static volatile boolean flag = true;

    public static void main(String[] args) {

        //创建第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行时，flag = "+flag);
            // 睡眠3秒
            try{ Thread.sleep(3000);}catch (Exception e){e.printStackTrace();}
            // 将运行标记设置为false
            flag = false;
            System.out.println(Thread.currentThread().getName()+"执行add()方法之后，flag = "+flag);
        },"线程1").start();

        //第二个是main线程
        while (true){
            // 如果第二个main线程 可以监测到flag值的改变，就会跳出当前循环，执行后续程序。
            if(!flag) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName()+"程序结束！");
    }
}
