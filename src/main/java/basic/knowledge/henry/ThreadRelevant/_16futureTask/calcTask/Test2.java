package basic.knowledge.henry.ThreadRelevant._16futureTask.calcTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<FutureTask<Integer>> futureTaskList = new ArrayList<FutureTask<Integer>>();
        for(int i = 0;i < 10 ;i ++){
            final int index = i;
            FutureTask<Integer> ft = new FutureTask<Integer>(new Callable<Integer>() {
                private  Integer result = 0;
                @Override
                public Integer call() throws Exception {
                    for(int j = 0;j < 100;j++){
                        result += j;
                    }
                    Thread.sleep(5000);
                    System.out.println("子线程：" + index + ",执行完成！sub task result is " + result);
                    return result;
                }
            });
            futureTaskList.add(ft);
            executorService.submit(ft);
        }

        System.out.println("子线程提交完毕，主线程继续执行！");

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行完毕！");

        Integer totalResult = 0;
        for(FutureTask<Integer> ft : futureTaskList){
            try {
                totalResult += ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        System.out.println("子线程计算的结果为：" + totalResult);
    }

}
