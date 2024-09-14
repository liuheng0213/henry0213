package basic.knowledge.henry.ThreadRelevant._00synchronized;

import java.util.concurrent.TimeUnit;

public class User {


    public  void getUp() {
        try {
            TimeUnit.SECONDS.sleep(2L); // 暂停2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("起床...");
    }

    public  void eatBreakfast() {
        System.out.println("吃早餐...");
    }
}
