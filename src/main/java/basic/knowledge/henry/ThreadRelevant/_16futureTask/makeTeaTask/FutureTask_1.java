package basic.knowledge.henry.ThreadRelevant._16futureTask.makeTeaTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask_1 需要执行的任务：洗水壶、烧开水、泡茶
 */
public class FutureTask_1 implements Callable<String> {

    // FutureTask_1 中持有 FutureTask_2 的引用
    FutureTask<String> futureTask_2;

    // 通过构造器初始化 成员变量
    public FutureTask_1(FutureTask<String> futureTask_2) {
        this.futureTask_2 = futureTask_2;
    }

    // 重写的 Callable 接口中的 call 方法。
    @Override
    public String call() throws Exception {

        System.out.println("T1：洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1：烧开水");
        TimeUnit.SECONDS.sleep(15);

        // 获取 T2 线程的茶叶
        String teas = futureTask_2.get();
        System.out.println("拿到茶叶：" + teas);

        System.out.println("T1：开始泡茶...");

        return "上茶：" + teas;
    }
}

