package basic.knowledge.henry.algorithm.aaaTest;

import java.util.Iterator;
import java.util.TreeSet;

public class ExamRoom {
    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        examRoom.seat();
        examRoom.seat();
        examRoom.seat();
        examRoom.seat();
        examRoom.leave(4);
        examRoom.seat();
    }
    TreeSet<Integer> seats;
    int maxSeat;

    public ExamRoom(int n) {
        seats = new TreeSet<>();
        maxSeat = n - 1;
    }

    public int seat() {
        if(seats.isEmpty()){
            seats.add(0);
            return 0;
        }

        int max = -1;
        int prev = -1;
        int seat = -1;
        Iterator<Integer> it = seats.iterator();
        while(it.hasNext()){
            int cur = it.next();
            if(cur!= 0 && prev == -1){
                max = cur;
                seat = 0;
            }else if(cur != 0 && prev != -1){
                int interval = (cur - prev)/2;
                if(interval > max){
                    max = interval;
                    seat = prev+ interval;
                }
            }
            prev = cur;

        }

        if(prev != maxSeat){
            if(maxSeat - prev > max){
                max = maxSeat - prev;
                seat = maxSeat;
            }
        }
        seats.add(seat);
        return seat;
    }

    public void leave(int p) {
        seats.remove(p);
    }
}
