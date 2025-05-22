package basic.knowledge.henry.ThreadRelevant._30tobedeleted._03futuretask;

import java.util.concurrent.Callable;

public class MuliplyTask implements Callable<Long> {
    int end;

    public MuliplyTask(int end) {
        this.end = end;
    }
    @Override
    public Long call() throws Exception {

        return (long) Math.pow(2,end);
    }
}
