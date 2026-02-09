package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord;

import java.util.*;

public class BookingSystemBetter {
    public static void main(String[] args) {
        BookingSystemBetter bookingSystem = new BookingSystemBetter();

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking(1, 9, 12));
        bookings.add(new Booking(2, 10, 11));
        bookings.add(new Booking(3, 12, 14));
        bookings.add(new Booking(4, 9, 10));
        bookings.add(new Booking(5, 11, 13));

        HashMap<Integer,List<Booking>> plan = bookingSystem.assignBookings(bookings);

        System.out.println(plan);
    }

    private HashMap<Integer, List<Booking>> assignBookings(List<Booking> bookings) {
        Collections.sort(bookings,(a,b)->a.getStart() - b.getStart());
        PriorityQueue<Booking> pq = new PriorityQueue<>((a,b)->a.getEnd() - b.getEnd());

        HashMap<Integer, List<Booking>> court2Bookings = new HashMap<>();
        HashMap<Integer,Integer> bookingId2CourtId = new HashMap<>();

        int courtId = 1;
        for(Booking curBooking: bookings){
            if(pq.isEmpty()){
                pq.add(curBooking);
                bookingId2CourtId.put(curBooking.getId(),courtId);
                court2Bookings.putIfAbsent(courtId,new ArrayList<>());
                court2Bookings.get(courtId).add(curBooking);
            }else if(pq.peek().getEnd() <= curBooking.getStart()){// curBooking will reuse the court
                Booking polledBooking = pq.poll();
                int usedCourtId = bookingId2CourtId.get(polledBooking.getId());
                bookingId2CourtId.put(curBooking.getId(),usedCourtId);
                court2Bookings.putIfAbsent(usedCourtId,new ArrayList<>());
                court2Bookings.get(usedCourtId).add(curBooking);
                pq.add(curBooking);
            }else{
                bookingId2CourtId.put(curBooking.getId(),++courtId);
                court2Bookings.putIfAbsent(courtId,new ArrayList<>());
                court2Bookings.get(courtId).add(curBooking);
                pq.add(curBooking);

            }
        }

        return court2Bookings;
    }


}
