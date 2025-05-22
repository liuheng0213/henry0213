package basic.knowledge.henry.ThreadRelevant._30tobedeleted._03futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CombineTask implements Callable<Long> {
    MuliplyTask muliplyTask;
    SumTask sumTask;
    ExecutorService executorService;
    public CombineTask(MuliplyTask muliplyTask, SumTask sumTask) {
        this.muliplyTask = muliplyTask;
        this.sumTask = sumTask;
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public Long call() throws Exception {
        long c1 =  executorService.submit(muliplyTask).get();
        long c2 = executorService.submit(sumTask).get();

        executorService.shutdown();
        return c1 + c2;
    }
}
