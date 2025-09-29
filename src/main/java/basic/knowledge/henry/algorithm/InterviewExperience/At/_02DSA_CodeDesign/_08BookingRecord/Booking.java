package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._08BookingRecord;

public class Booking {
    private final int start, end;

    private final int id; //id of the booking

    public Booking(int id,int start, int end) {
        this.start = start;
        this.end = end;
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }
}
