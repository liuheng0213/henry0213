package basic.knowledge.henry.ThreadRelevant._16futureTask.makeTeaTask;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 这里需要注意的是 FutureTask_1 这个任务在执行泡茶任务前，需要等待 FutureTask_2 把茶叶拿来，
 * 所以 FutureTask_1 内部需要引用 FutureTask_2，并在执行泡茶之前，
 * 我们可以充分利用 Future 的 get 方法的阻塞性质来调用 FutureTask_2 的get()方法实现等待。
 */
public class MakeTea{
        public static void main(String[]args){
                // 创建 FutureTask_2 的任务
                FutureTask<String> futureTask_2 = new FutureTask<>(new FutureTask_2());
                // 创建 FutureTask_1 的任务，并将 FutureTask_2 任务的引用传入
                FutureTask<String> futureTask_1 = new FutureTask<>(new FutureTask_1(futureTask_2));

                // 创建线程 T1，来执行任务 FutureTask_1
                Thread t1 = new Thread(futureTask_1);
                t1.start();

                // 创建线程 T2，来执行任务 FutureTask_2
                Thread t2 = new Thread(futureTask_2);
                t2.start();

                try {
                        // 获取任务 FutureTask_1 的最后一步的结果
                        System.out.println(futureTask_1.get());
                } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                }
        }


}
