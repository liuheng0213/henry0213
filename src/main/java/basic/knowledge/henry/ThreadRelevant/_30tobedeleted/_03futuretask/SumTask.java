package basic.knowledge.henry.ThreadRelevant._30tobedeleted._03futuretask;

import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {
    int end;

    public SumTask(int end) {
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i = 1;i<= end;i++){
            sum+= i;
        }
        return sum;
    }
}
