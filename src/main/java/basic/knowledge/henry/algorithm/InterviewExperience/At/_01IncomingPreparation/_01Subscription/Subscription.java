package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._01Subscription;

import java.util.Date;

public class Subscription {
    String planId;

    String date; ////  YYYY-MM-DD format

    public Subscription(String planId, String date) {
        this.planId = planId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "planId='" + planId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
