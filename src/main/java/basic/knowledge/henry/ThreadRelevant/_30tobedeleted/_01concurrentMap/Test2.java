package basic.knowledge.henry.ThreadRelevant._30tobedeleted._01concurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {
   ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap(0);


   /**
    * is this thread safe?
    */
   public void add(){
      synchronized (concurrentHashMap){
         int val = 0;
         for(int i =0;i< 100;i++){
            val = concurrentHashMap.getOrDefault("c",0);
            concurrentHashMap.put("c",val + 1);
         }
      }

   }


   public static void main(String[] args) {
      new Test2().test();

   }

   private void test() {
      ExecutorService executorService = Executors.newFixedThreadPool(4);
      for(int i =0;i< 5;i++){
         executorService.submit(()->add());
      }

      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
      System.out.println(concurrentHashMap.get("c"));
      executorService.shutdown();
   }

}
