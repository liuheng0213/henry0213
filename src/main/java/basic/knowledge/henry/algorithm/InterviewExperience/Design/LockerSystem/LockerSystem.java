package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem;

import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.Locker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.MediumParcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.Parcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.SmallParcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * one points to many(one)?
 * lock table : lockid, type
 * parcel table:  pid, type
 * usage recording  id, lockid pid active(0 or 1)
 */
public class LockerSystem {

    Map<String,Locker> storedP2LMap = new HashMap<>();// parcel stored to locker map
    Map<String,Parcel> storedL2PMap = new HashMap<>();// locker used to stored parcel map
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
        // update storedP2LMap and storedL2PMap
        takeOutParcel(lockerToOpen,parcel);
    }

    private void takeOutParcel(Locker lockerToOpen, Parcel parcel) {
        storedP2LMap.remove(parcel.getId());
        storedL2PMap.remove(lockerToOpen.getLockerId());
    }

    public Locker findLockerForDelivery(Parcel parcel){
        Locker locker;
        if(parcel instanceof SmallParcel){
            locker =findLockerBytype(SizeEnum.SMALL);
            if(locker == null) {
                locker =  findLockerBytype(SizeEnum.MEDIUM);
                if (locker == null) {
                    locker =  findLockerBytype(SizeEnum.LARGE);
                }
            }
        }else if(parcel instanceof MediumParcel){
            locker =  findLockerBytype(SizeEnum.MEDIUM);
            if(locker == null){
                locker =  findLockerBytype(SizeEnum.LARGE);
            }
        }else{
            locker = findLockerBytype(SizeEnum.LARGE);
        }
        //update storedP2LMap and storedL2PMap
        storeParcel(locker,parcel);

        return locker;
    }

    private Locker findLockerBytype(SizeEnum type) {
        List<Locker>  list = lockers.get(type);
        for(Locker l : list){
            if(!storedL2PMap.containsKey(l.getLockerId())){
                return l;
            }
        }
        return null;
    }



    private void storeParcel(Locker locker, Parcel parcel) {
        storedP2LMap.put(parcel.getId(),locker);
        storedL2PMap.put(locker.getLockerId(),parcel);
    }


}
