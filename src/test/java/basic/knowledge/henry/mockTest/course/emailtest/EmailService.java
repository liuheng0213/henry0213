package basic.knowledge.henry.mockTest.course.emailtest;

public class EmailService {

    private DeliveryPlatform deliveryPlatform;

    public EmailService(DeliveryPlatform deliveryPlatform) {
        this.deliveryPlatform = deliveryPlatform;
    }

    public void send(String to, String subject, String body, boolean html) {
        EmailStyle emailStyle = EmailStyle.DOC;
        if(html) {
            emailStyle = EmailStyle.HTML;
        }

        Email email = new Email(to, subject, body);
        email.setEmailStyle(emailStyle);
        deliveryPlatform.deliver(email);
    }
}

