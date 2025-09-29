package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord;

import java.util.HashMap;
import java.util.*;

/**
 * 需要新开几个court
 * a) Implement a function that given a list of tennis court bookings with start and finish times, returns a plan assigning each booking to a specific court, ensuring each court is used by only one booking at a time and using the minimum amount of courts with unlimites number of courts available.
 * An example of the booking record might look like
 *
 * b) After each booking, a fixed amount of time, Y, is needed to maintain the court before it can be rented again
 * c) Court only need maintenance after X amount of usage
 * How would you modify the code if each court also had a Y maintenance time that occurred after X bookings?
 * The function should now become something like
 *
 * d) The original problem can be made simpler by removing the “assigning each booking to a specific court” part.
 * The candidate needs to find the minimum number of courts needed to accommodate all the bookings
 * e) Check if booking conflict - Write a function that if given two bookings to check if they conflict with each other
 *
 * return Hashmap<Integer,Integer>,key is the Booking id, value is the court this booking is specified to
 */
public class BookingTennisCourt {
    public static void main(String[] args) {
        BookingTennisCourt bookingTennisCourt = new BookingTennisCourt();
        Booking b1 = new Booking(1,10,20);
        Booking b2 = new Booking(2,5,12);
        Booking b3 = new Booking(3,13,30);
        Booking b4 = new Booking(4,14,30);
        Booking b5 = new Booking(5,31,35);
        Booking b6 = new Booking(6,36,39);

        List<Booking> bookings = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5));

        HashMap<Integer,List<Integer>> plan = bookingTennisCourt.assignBookings(bookings,3,2);

        System.out.println(plan);
    }

    public  HashMap<Integer,List<Integer>>  assignBookings(List<Booking> bookings,int maintenanceTime,int times){
        Collections.sort(bookings,(a,b)->a.getStart()- b.getStart());

        PriorityQueue<Booking> pq = new PriorityQueue<>((a,b)->a.getEnd()-b.getEnd());

        int courtId = 0;
        HashMap<Integer,Integer> booking2court = new HashMap<>();
        HashMap<Integer,Integer> courtUsage = new HashMap<>();//store court to count of usage
        for(int i = 0;i< bookings.size();i++){
            Booking curBooking = bookings.get(i);
            int curBookingId = curBooking.getId();
            if(pq.isEmpty()){
                pq.add(curBooking);
                booking2court.putIfAbsent(curBookingId,courtId);
                courtUsage.put(courtId,1);
            }else{
                Booking bookingPossibleOutOfCourt = pq.peek();
                int occupiedCourtId = booking2court.get(bookingPossibleOutOfCourt.getId());
                //the court where bookingPossibleOutOfCourt is in can be reused by the  curBooking
                if(bookingPossibleOutOfCourt.getEnd() + maintenanceTime <= curBooking.getStart() && courtUsage.get(occupiedCourtId)>= times){
                    pq.poll();
                    pq.add(curBooking);
                    booking2court.put(curBookingId,occupiedCourtId);
                    courtUsage.put(occupiedCourtId,courtUsage.get(occupiedCourtId) + 1);
                }else{
                    pq.add(curBooking);
                    courtId++;
                    booking2court.put(curBookingId,courtId);
                    courtUsage.put(courtId,1);
                }
            }
        }

        return convert2Plan(booking2court);

    }

    private HashMap<Integer, List<Integer>> convert2Plan(HashMap<Integer, Integer> booking2court) {
        HashMap<Integer, List<Integer>> plan = new HashMap<>();//courtid to bookingid
        for(Integer bookingId : booking2court.keySet()){
            int courtId = booking2court.get(bookingId);
            plan.putIfAbsent(courtId,new ArrayList<>());
            plan.get(courtId).add(bookingId);
        }

        return plan;
    }
}

