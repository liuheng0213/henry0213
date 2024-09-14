package basic.knowledge.henry.ThreadRelevant._28VolatileAndAtomic.volatile_Usage;


/**
 * 为什么加了锁就能获取到最新的值了呢？
 *
 * 因为线程进入 synchronized 代码块之后，它的执行过程如下：
 *
 * 线程获取锁
 * 从主内存拷贝共享数据的最新值到工作内存中
 * 执行代码
 * 将修改后的值刷新到主内存
 * 线程释放锁
 *
 * 解释： 为什么此例仅在主线程加锁就能实现线程安全
 * 主线程拿到锁以后
 * flag 有可能锁false 也有可能是true 但是这并不重要
 * 主线程锁的是while里的代码块， 即便thread1 对flag的更改还没有更新到主存， 也迟早会更新到主存，从而break出while
 */
public class VolatileMainAddLock {
    private static boolean flag = true; // unnecessary add volatile, because main thread is using synchronized
    public static void main(String[] args) {
        //创建第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行时，flag = "+flag);
            try{ Thread.sleep(5000);}catch (Exception e){e.printStackTrace();}
            flag = false;
            System.out.println(Thread.currentThread().getName()+"执行add()方法之后，flag = "+flag);
        },"线程1").start();

        //第二个是main线程
        while (true){
            // 加锁
            synchronized (VolatileMainAddLock.class) {
                if(!flag) {
                    break;
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+"程序结束！");
    }
}
