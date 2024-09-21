package basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem;

import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.Locker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.MediumLocker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.lock.SmallLocker;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.MediumParcel;
import basic.knowledge.henry.algorithm.InterviewExperience.Design.LockerSystem.parcel.SmallParcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {


    public static void main(String[] args) {
        Map<SizeEnum, List<Locker>> lockers = new HashMap<>();
        lockers.put(SizeEnum.SMALL,new ArrayList<>());

        lockers.put(SizeEnum.MEDIUM,new ArrayList<>());
        lockers.get(SizeEnum.SMALL).add(new SmallLocker("S1"));
        lockers.get(SizeEnum.SMALL).add(new SmallLocker("S2"));
        lockers.get(SizeEnum.SMALL).add(new SmallLocker("S3"));
        lockers.get(SizeEnum.SMALL).add(new SmallLocker("S4"));
        lockers.get(SizeEnum.SMALL).add(new SmallLocker("S5"));


        lockers.get(SizeEnum.MEDIUM).add(new MediumLocker("M1"));
        lockers.get(SizeEnum.MEDIUM).add(new MediumLocker("M2"));
        lockers.get(SizeEnum.MEDIUM).add(new MediumLocker("M3"));
        lockers.get(SizeEnum.MEDIUM).add(new MediumLocker("M4"));
        lockers.get(SizeEnum.MEDIUM).add(new MediumLocker("M5"));
        LockerSystem lockerSystem = new LockerSystem(lockers);


        Locker p1 = lockerSystem.findLockerForDelivery(new SmallParcel("PS1"));
        System.out.println(p1);
        Locker p2 = lockerSystem.findLockerForDelivery(new SmallParcel("PS2"));
//        System.out.println(p2);
//        Locker p3 = lockerSystem.findLockerForDelivery(new SmallParcel("PS3"));
//        System.out.println(p3);
        Locker p4 = lockerSystem.findLockerForDelivery(new SmallParcel("PS4"));
        System.out.println(p4);
//        Locker p5 = lockerSystem.findLockerForDelivery(new SmallParcel("PS5"));
//        System.out.println(p5);
//        Locker p6 = lockerSystem.findLockerForDelivery(new SmallParcel("PS6"));
//        System.out.println(p6);
        Locker p7 = lockerSystem.findLockerForDelivery(new SmallParcel("PS7"));
        System.out.println(p7);

        Locker p8 = lockerSystem.findLockerForDelivery(new MediumParcel("PM1"));
        System.out.println(p8);

        lockerSystem.openAndPickUp(new SmallParcel("PS4"));
//        lockerSystem.openAndPickUp(new SmallParcel("PS4"));
        Locker p100 = lockerSystem.findLockerForDelivery(new SmallParcel("PS4"));
        System.out.println(p100);

//        lockerSystem.openAndPickUp(new SmallParcel("PS4"));
        System.out.println("====");





    }
}
