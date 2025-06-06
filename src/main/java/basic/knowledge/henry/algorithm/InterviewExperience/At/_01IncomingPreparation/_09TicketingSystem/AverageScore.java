package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._09TicketingSystem;


public class AverageScore implements Comparable<AverageScore>{
    Double avg;
    Double count;
    Double totalScore;

    public AverageScore() {

        this.avg = 0.0;
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
    public int compareTo(AverageScore that) {
        return that.avg.compareTo(this.avg);
    }
}
