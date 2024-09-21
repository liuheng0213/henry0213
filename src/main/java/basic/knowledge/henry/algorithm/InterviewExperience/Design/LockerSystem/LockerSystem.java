package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem;

import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.Locker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.MediumLocker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.SmallLocker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.MediumParcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.Parcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.SmallParcel;

import java.util.*;

public class LockerSystem {

    Map<String,Locker> storedP2LMap = new HashMap<>();// parcel stored to locker map
    Map<SizeEnum, List<Locker>> lockers = new HashMap<>();

    public LockerSystem(Map<SizeEnum, List<Locker>> lockers) {
        this.lockers = lockers;
    }

    public void openAndPickUp(Parcel parcel){
        Locker lockerToOpen = null;
        if(storedP2LMap.containsKey(parcel.getId())){
            lockerToOpen= storedP2LMap.get(parcel.getId());
        }else {
            throw new RuntimeException("parcel is not in the lockers");
        }
        lockerToOpen.takeOutParcel(parcel);

        //update storedP2LMap
        storedP2LMap.remove(parcel.getId());

    }

    public Locker findLockerForDelivery(Parcel parcel){
        Locker locker;
        if(parcel instanceof SmallParcel){
            locker = (SmallLocker) findFromSmallLocker();
            if(locker == null){
                locker = (MediumLocker) findFromMediumLocker();
            }
        }else{
            locker = (MediumLocker)findFromMediumLocker();
        }
        locker.storeParcel(parcel);

        //update p2lMap
        storedP2LMap.put(parcel.getId(),locker);
        return locker;
    }

    private Locker findFromMediumLocker() {
        List<Locker>  list = lockers.get(SizeEnum.MEDIUM);
        for(Locker l : list){
            if(!l.isUsed()){
                return l;
            }
        }
        return null;
    }

    private Locker findFromSmallLocker() {
        List<Locker> list = lockers.get(SizeEnum.SMALL);
        for(Locker l : list){
            if(!l.isUsed()){
                return l;
            }
        }
        return null;
    }
}
