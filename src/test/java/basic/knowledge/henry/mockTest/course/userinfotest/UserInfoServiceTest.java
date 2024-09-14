package basic.knowledge.henry.mockTest.course.userinfotest;

import basic.knowledge.henry.mockTest.course.emailtest.Email;
import basic.knowledge.henry.mockTest.course.emailtest.EmailStyle;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {
    @Mock
     UserInfoDao userInfoDao;
    @InjectMocks
     UserInfoService userInfoService;




    @Test
    public void testPrint() {
        MockitoAnnotations.initMocks(this);
        userInfoService.setUserInfoDao(userInfoDao);
        UserInfo userInfo = new UserInfo("admin", "123");

        when(userInfoDao.select()).thenReturn(userInfo);

        userInfoService.printInfo();



    }
}

