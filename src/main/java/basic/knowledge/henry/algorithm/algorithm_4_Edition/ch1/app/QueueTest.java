package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch1.app;




import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch1.queue.MyQueue;
import basic.knowledge.henry.algorithm.algorithm_4_Edition.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class QueueTest {
    @Test
    public void testQueue(){
        MyQueue<User> myqueue = new MyQueue<>();
        myqueue.enqueue(new User("1"));
        myqueue.enqueue(new User("9"));
        myqueue.enqueue(new User("3"));
        myqueue.enqueue(new User("4"));
        myqueue.enqueue(new User("5"));
        myqueue.enqueue(new User("2"));

//        Iterator<User> iterator = myqueue.iterator();
//        while(iterator.hasNext()){
//            User user = iterator.next();
//            System.out.println(user);
//        }
//        System.out.println("-------------");
//        User first = myqueue.dequeue();
//        System.out.println(first);

        User user1 = myqueue.findAndDeleteByIndex(5);
        System.out.println(user1);
        System.out.println("-------------");
        Iterator<User> iterator2 = myqueue.iterator();
        while(iterator2.hasNext()){
            User user = iterator2.next();
            System.out.println(user);
        }

        System.out.println(myqueue.size());
    }
}
