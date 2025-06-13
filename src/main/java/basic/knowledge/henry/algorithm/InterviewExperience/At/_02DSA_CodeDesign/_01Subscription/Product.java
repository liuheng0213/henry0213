package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription;

public class Product {
    String name;

    Subscription subscription;

    public Product(String name, Subscription subscription) {
        this.name = name;
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", subscription=" + subscription +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
