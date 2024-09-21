package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel;

public class LargeParcel implements Parcel{
    String pid;

    public LargeParcel(String pid) {
        this.pid = pid;
    }

    @Override
    public String getId() {
        return this.pid;
    }

    @Override
    public String toString() {
        return "LargeParcel{" +
                "pid='" + pid + '\'' +
                '}';
    }
}
