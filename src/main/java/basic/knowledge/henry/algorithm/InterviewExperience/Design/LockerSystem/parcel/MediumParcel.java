package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel;

public class MediumParcel implements Parcel {
    String pid;

    public MediumParcel(String pid) {
        this.pid = pid;
    }

    @Override
    public String getId() {
        return this.pid;
    }

    @Override
    public String toString() {
        return "MediumParcel{" +
                "pid='" + pid + '\'' +
                '}';
    }
}
