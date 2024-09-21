package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock;

import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.MediumParcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.Parcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.SmallParcel;

import java.util.Objects;

public class MediumLocker implements Locker{
    String lockId;
    boolean isused = false;
    Parcel parcel = null;

    public MediumLocker(String lockId) {
        this.lockId = lockId;
    }

    @Override
    public void storeParcel(Parcel parcel){
        if(parcel instanceof SmallParcel || parcel instanceof MediumParcel){
            this.parcel =parcel;
            isused = true;
        }else{
            throw new RuntimeException(" size does not match");
        }

    }

    @Override
    public void takeOutParcel(Parcel parcel) {
        isused = false;
        this.parcel = null;
    }

    @Override
    public boolean isUsed() {
        return isused;
    }

    @Override
    public String getLockerId() {
        return this.lockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediumLocker that = (MediumLocker) o;
        return Objects.equals(lockId, that.lockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockId);
    }

    @Override
    public String toString() {
        return "MediumLocker{" +
                "lockId='" + lockId + '\'' +
                ", isused=" + isused +
                ", parcel=" + parcel +
                '}';
    }
}
