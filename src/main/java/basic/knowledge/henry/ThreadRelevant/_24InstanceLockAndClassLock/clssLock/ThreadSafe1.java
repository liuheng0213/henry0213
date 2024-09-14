package basic.knowledge.henry.ThreadRelevant._24InstanceLockAndClassLock.clssLock;

public class ThreadSafe1 implements Runnable{
     static  int ticket = 100;
    @Override
    public void run() {
        sellTicket();//设置线程任务是调用addSalary接口
    }

    public static  synchronized void sellTicket(){
        while (ticket > 0){

            if(ticket > 0) ticket--;//当票数大于0的时候，就可以卖票
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在卖" + ticket + "张票");
        }
    }

    public static void main(String[] args) {
        ThreadSafe1 ts = new ThreadSafe1();//创建一个ts对象
        ThreadSafe1 ts1 = new ThreadSafe1();//创建另一个对象 static is necessary
        new Thread(ts).start();
        new Thread(ts1).start();
    }
}

