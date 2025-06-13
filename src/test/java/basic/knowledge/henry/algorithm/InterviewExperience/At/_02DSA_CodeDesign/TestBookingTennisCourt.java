package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign;

import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord.Booking;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord.BookingTennisCourt;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestBookingTennisCourt {

    BookingTennisCourt bt = new BookingTennisCourt();

    @Test
    public void testAssignBookings(){
        //[[0,30],[5,10],[15,20]]
        Booking booking1 = new Booking(0,30);
        Booking booking5 = new Booking(30,45);
        Booking booking2 = new Booking(5,10);
        Booking booking3 = new Booking(15,20);
        Booking booking4 = new Booking(11,14);
        Booking booking6 = new Booking(16,19);
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking1);
        bookingList.add(booking2);
        bookingList.add(booking3);
        bookingList.add(booking4);
        bookingList.add(booking5);
        bookingList.add(booking6);

        HashMap<Integer, List<Booking>> integerListHashMap = bt.assignBookings(bookingList);


        System.out.println(integerListHashMap);
    }
}
