package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock;



public class MediumLocker implements Locker{
    String lid;

    public MediumLocker(String lid) {
        this.lid = lid;
    }

//
    @Override
    public String getLockerId() {
        return this.lid;
    }


    @Override
    public String toString() {
        return "MediumLocker{" +
                "lid='" + lid + '\'' +
                '}';
    }
}
