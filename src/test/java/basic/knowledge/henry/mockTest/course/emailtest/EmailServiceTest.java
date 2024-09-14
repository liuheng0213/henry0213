package basic.knowledge.henry.mockTest.course.emailtest;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // make @mock @injectmock effective
public class EmailServiceTest {

    @Mock
    private DeliveryPlatform deliveryPlatform;

    @InjectMocks
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<Email> emailArgumentCaptor;

    /**
     * 现在我想验证一个问题，当我发送HTML邮件时，deliver这个函数收到的email到底是不是HTML类型的。
     * 这种情况下，就可以通过ArgumentCaptor的方式来解决了。
     */

    @Test
    public void testHtmlEmail() {
        emailService.send("某人", "无题", "无内容", true);

        /**
         *  Email email = new Email(to, subject, body);
         *         email.setEmailStyle(emailStyle);
         *         deliveryPlatform.deliver(email);
         *         here capture's the email argument
         */
        verify(deliveryPlatform).deliver(emailArgumentCaptor.capture());

        // this is the captured email
        Email email = emailArgumentCaptor.getValue();
        Assertions.assertEquals(EmailStyle.HTML, email.getEmailStyle());
    }
}

