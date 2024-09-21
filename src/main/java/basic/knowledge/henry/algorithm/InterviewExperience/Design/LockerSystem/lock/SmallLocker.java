package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock;

public class SmallLocker implements Locker {
    String lid;


    public SmallLocker(String lid) {
        this.lid = lid;
    }

    @Override
    public String getLockerId() {
        return this.lid;
    }


    @Override
    public String toString() {
        return "SmallLocker{" +
                "lid='" + lid + '\'' +
                '}';
    }
}
