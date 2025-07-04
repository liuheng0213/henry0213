package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord;

import java.util.*;

public class BookingTennisCourt {

    public HashMap<Integer,List<Booking>> assignBookings(List<Booking> bookings){
        // Step 1: Sort bookings by start time, occupy the room firstly
        Collections.sort(bookings,(a,b)->a.getStart() - b.getStart());
//        bookings.sort(Comparator.comparingInt(b -> b.start));

        // Step 2: Priority queue to track courts by end time,
        // any room which polled out can be reused by the following book
        PriorityQueue<Booking> courtQueue = new PriorityQueue<>((a,b)->a.getEnd()-b.getEnd());


        //this design is not good, same booking might be sent twice
//        HashMap<Booking,Integer> booking2Court = new HashMap<>();

        //this design is better, same booking might be sent twice,need CLARIFICATION
        courtQueue.add(bookings.get(0));
        int idx = 1;
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(bookings.get(0));
        bookings.get(0).courtIds.add(idx);
//        HashMap<Integer,List<Booking>> res2 = new HashMap<>();
//        List<Booking> bookingsInIdx = res2.computeIfAbsent(idx, k -> new ArrayList<>());
//        bookingsInIdx.add(bookings.get(0));
        for(int i = 1;i< bookings.size();i++){
            Booking bookingInCounrt = courtQueue.peek();
            Booking booking = bookings.get(i);
            if(bookingInCounrt.getEnd() <= booking.getStart()){
                Booking polledBooking = courtQueue.poll();
                Integer id = polledBooking.courtIds.get(0);
                booking.courtIds.add(id);
                bookingList.add(booking);
//                res2.get(courtId).add(bookings.get(i));
                courtQueue.add(booking);

            }else{
                Booking booking1 = new Booking(booking.getStart(), booking.getEnd());
                courtQueue.add(booking1);
                idx++;
                booking1.courtIds.add(idx);
                bookingList.add(booking1);
            }
        }


        HashMap<Integer,List<Booking>> res = new HashMap<>();
        for(Booking booking: bookingList){
            List<Integer> courtIds = booking.courtIds;
            for(int id: courtIds){
                List<Booking> bookings1 = res.computeIfAbsent(id, k -> new ArrayList<>());
                bookings1.add(booking);
            }

        }

        return res;
    }


    public HashMap<Integer,List<Booking>> assignBookings2(List<Booking> bookings){
        // Step 1: Sort bookings by start time, occupy the room firstly
        Collections.sort(bookings,(a,b)->a.getStart() - b.getStart());
//        bookings.sort(Comparator.comparingInt(b -> b.start));

        // Step 2: Priority queue to track courts by end time,
        // any room which polled out can be reused by the following book
        PriorityQueue<Booking> courtQueue = new PriorityQueue<>((a,b)->a.getEnd()-b.getEnd());


        //this design is not good, same booking might be sent twice
//        HashMap<Booking,Integer> booking2Court = new HashMap<>();

        //this design is better, same booking might be sent twice,need CLARIFICATION
        HashMap<Booking,HashSet<Integer>> booking2Court = new HashMap<>();
        courtQueue.add(bookings.get(0));
        int idx = 1;
        booking2Court.putIfAbsent(bookings.get(0), new HashSet<>());
        booking2Court.get(bookings.get(0)).add(idx);
//        HashMap<Integer,List<Booking>> res2 = new HashMap<>();
//        List<Booking> bookingsInIdx = res2.computeIfAbsent(idx, k -> new ArrayList<>());
//        bookingsInIdx.add(bookings.get(0));
        for(int i = 1;i< bookings.size();i++){
            Booking bookingInCounrt = courtQueue.peek();
            Booking booking = bookings.get(i);
            if(bookingInCounrt.getEnd() <= booking.getStart()){
                Booking polledBooking = courtQueue.poll();
                HashSet<Integer> courtIds = booking2Court.get(polledBooking);
//                res2.get(courtId).add(bookings.get(i));
                int id = courtIds.iterator().next();
                booking2Court.putIfAbsent(booking,new HashSet<>());
                booking2Court.get(booking).add(id);
                courtQueue.add(booking);

            }else{
                courtQueue.add(booking);
                idx++;
                booking2Court.putIfAbsent(booking, new HashSet<>());
                booking2Court.get(booking).add(idx);
            }
        }


        HashMap<Integer,List<Booking>> res = new HashMap<>();
        for(Map.Entry<Booking,HashSet<Integer>> entry : booking2Court.entrySet()){
            HashSet<Integer> courtIds = entry.getValue();
            Booking booking = entry.getKey();
            for(int id: courtIds){
                List<Booking> bookings1 = res.computeIfAbsent(id, k -> new ArrayList<>());
                bookings1.add(booking);
            }

        }

        return res;
    }
}
