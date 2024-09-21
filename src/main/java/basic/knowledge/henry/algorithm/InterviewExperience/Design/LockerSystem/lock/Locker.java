package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock;

import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.Parcel;

public interface Locker {
    void storeParcel(Parcel parcel);
    void takeOutParcel(Parcel parcel);

    boolean isUsed();

    String getLockerId();
}
