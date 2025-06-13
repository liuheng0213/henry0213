package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord;

import java.util.Objects;

public class Booking {
    private final int start, end;
    public Booking(int start, int end) {
        this.start = start;
        this.end = end;
    }


    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return start == booking.start && end == booking.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
