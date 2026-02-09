package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._09TicketingSystem;


public class AScore implements Comparable<AScore>{
    Double avg;
    Double count;
    Double totalScore;

    int lastRating;

    public AScore() {
        this.avg = 0.0;
    }

    public AScore(Double avg, Double count, Double totalScore) {
        this.avg = avg;
        this.count = count;
        this.totalScore = totalScore;
    }

    public void updateAvg(){
        avg = this.totalScore / this.count;
    }


    @Override
    public String toString() {
        return "AverageScore{" +
                "avg=" + avg +
                ", count=" + count +
                ", totalScore=" + totalScore +
                '}';
    }

    @Override
    public int compareTo(AScore that) {
        return that.avg.compareTo(this.avg);
    }
}
