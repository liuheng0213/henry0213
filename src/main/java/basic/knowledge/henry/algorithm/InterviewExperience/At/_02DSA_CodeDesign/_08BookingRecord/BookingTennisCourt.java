package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord;

import java.util.*;

public class BookingTennisCourt {

    public HashMap<Integer,List<Booking>> assignBookings(List<Booking> bookings){
        // Step 1: Sort bookings by start time
        Collections.sort(bookings,(a,b)->a.getStart() - b.getStart());
//        bookings.sort(Comparator.comparingInt(b -> b.start));

        // Step 2: Priority queue to track courts by end time
        PriorityQueue<Booking> courtQueue = new PriorityQueue<>((a,b)->a.getEnd()-b.getEnd());

        HashMap<Booking,Integer> booking2Court = new HashMap<>();
        courtQueue.add(bookings.get(0));
        int idx = 1;
        booking2Court.put(bookings.get(0), idx);
        for(int i = 1;i< bookings.size();i++){
            Booking bookingInCounrt = courtQueue.peek();
            Booking booking = bookings.get(i);
            if(bookingInCounrt.getEnd() <= booking.getStart()){
                Booking polledBooking = courtQueue.poll();
                Integer courtId = booking2Court.get(polledBooking);
                booking2Court.put(booking,courtId);
                courtQueue.add(booking);
            }else{
                courtQueue.add(booking);
                idx++;
                booking2Court.put(booking, idx);
            }
        }


        HashMap<Integer,List<Booking>> res = new HashMap<>();
        for(Map.Entry<Booking,Integer> entry : booking2Court.entrySet()){
            int courtID = entry.getValue();
            Booking booking = entry.getKey();
            List<Booking> bookings1 = res.computeIfAbsent(courtID, k -> new ArrayList<>());
            bookings1.add(booking);
        }

        return res;
    }
}
