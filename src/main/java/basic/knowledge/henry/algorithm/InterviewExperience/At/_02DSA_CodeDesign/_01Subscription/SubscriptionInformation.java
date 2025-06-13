package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription;


public class SubscriptionInformation {
    String customerID;

    Product product;

    public SubscriptionInformation(String customerID, Product product) {
        this.customerID = customerID;
        this.product = product;
    }


    @Override
    public String toString() {
        return "SubscriptionInformation{" +
                "customerID='" + customerID + '\'' +
                ", product=" + product +
                '}';
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
