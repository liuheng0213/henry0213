package basic.knowledge.henry.dynamic_Proxy.cglib_proxy;

public class ForumServiceImpl implements ForumService {
    @Override
    public void remiveTopic(int topicId) {
        System.out.println("删除了topic,是record:"+topicId);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeForum(int forumId) {
        System.out.println("Forum,是record:"+forumId);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
