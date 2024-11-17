package basic.knowledge.henry.algorithm.InterviewExperience.ama;

public class Test20_processOrder_executionOrder {
    public static void main(String[] args) {
        int[] processingOrder = new int[]{2,3,5,1,4};
        int[] executionOrder = new int[]{2,5,3,4,1};
        int i = new Test20_processOrder_executionOrder().countFailedProcesses(processingOrder, executionOrder);
        System.out.println(i);
    }

    private  int countFailedProcesses(int[] processingOrder, int[] executionOrder) {
        int processes = processingOrder.length;
        int[] dependentProcessesCutOffIdxTracker = new int[processes+1];
        int[] processToExecutionOrderIdx = new int[processes+1];

        for(int i=0 ; i<processes; i++) {
            processToExecutionOrderIdx[executionOrder[i]] = i;
        }

        dependentProcessesCutOffIdxTracker[0] = -1;
        for(int i=1 ; i<processes; i++) {
            int process = processingOrder[i-1];
            dependentProcessesCutOffIdxTracker[i] = Math.max(dependentProcessesCutOffIdxTracker[i-1], processToExecutionOrderIdx[process]);
        }

        int failedProceeseCount = 0;
        for(int i=0 ; i<processes; i++) {
            int process = processingOrder[i];
            int processOrderInEo = processToExecutionOrderIdx[process];
            int dependentProcessesCutOffIdx = dependentProcessesCutOffIdxTracker[i];

            if (processOrderInEo <= dependentProcessesCutOffIdx) {
                failedProceeseCount++;
            }
        }

        return failedProceeseCount;
    }
}
