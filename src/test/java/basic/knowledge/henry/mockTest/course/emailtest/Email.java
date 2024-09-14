package basic.knowledge.henry.mockTest.course.emailtest;


public class Email {

    private String to;
    private String subject;
    private String body;
    private EmailStyle emailStyle;

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public Email() {
    }

    public void setEmailStyle(EmailStyle emailStyle) {
        this.emailStyle = emailStyle;
    }

    public EmailStyle getEmailStyle() {
        return this.emailStyle;
    }
}
