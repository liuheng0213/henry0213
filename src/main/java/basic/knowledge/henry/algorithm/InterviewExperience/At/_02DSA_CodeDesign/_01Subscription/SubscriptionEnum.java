package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription;

public enum SubscriptionEnum {
    BASIC(5.0),
    STANDARD(10.0),
    PREMIUM(15.0);

    private double value;

    private SubscriptionEnum(Double value){
        this.value =value;
    }


    public Double getValue(){
        return this.value;
    }

}
