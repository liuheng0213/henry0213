package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel;

public class SmallParcel implements Parcel {
    String pid;

    public SmallParcel(String pid) {
        this.pid = pid;
    }

    @Override
    public String getId() {
        return this.pid;
    }

    @Override
    public String toString() {
        return "SmallParcel{" +
                "pid='" + pid + '\'' +
                '}';
    }
}
