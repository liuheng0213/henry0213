package basic.knowledge.henry.ThreadRelevant._30tobedeleted._03futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实现一个计算任务。 必须先把
 * 1～10 之和计算出来 得出c1
 * 再把
 * 2～6 2 的 6 次方 计算出来 得出c2
 * 上述两部需可以并行计算
 *
 * 然后汇总 得出c2 + c1
 *
 * 以上步骤也需要并行计算5次，然后主线程汇总
 */
public class Test {
    public static void main(String[] args) {
        try {
            new Test().test();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MuliplyTask muliplyTask = new MuliplyTask(6);
        SumTask sumTask = new SumTask(10);

        long total = 0;
        for(int i = 0;i< 5;i++){
            total +=executorService.submit(new CombineTask(muliplyTask,sumTask)).get();
        }
        executorService.shutdown();
        System.out.println(total);
    }
}
