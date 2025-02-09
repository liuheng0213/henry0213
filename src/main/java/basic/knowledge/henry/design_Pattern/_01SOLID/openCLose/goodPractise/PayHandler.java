package basic.knowledge.henry.design_Pattern._01SOLID.openCLose.goodPractise;

import java.util.HashMap;

public class PayHandler {
    private HashMap<String,PayProcessor> processors;

    public PayHandler() {
        this.processors = new HashMap<>();
    }

    public void register(String type,PayProcessor payProcessor){
        processors.put(type,payProcessor);
    }
    public void pay(String type){
        PayProcessor payProcessor = processors.get(type);
        payProcessor.handle();
    }
}
