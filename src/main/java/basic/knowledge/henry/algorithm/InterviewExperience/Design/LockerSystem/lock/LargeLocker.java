package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock;

public class LargeLocker implements Locker{
    String lid;

    public LargeLocker(String lid) {
        this.lid = lid;
    }

    @Override
    public String getLockerId() {
        return this.lid;
    }

    @Override
    public String toString() {
        return "LargeLocker{" +
                "lid='" + lid + '\'' +
                '}';
    }
}
