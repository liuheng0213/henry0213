package basic.knowledge.henry.mockTest.course.userinfotest;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserInfoDaoTest {
    @Test
    public void testSelect(){
        UserInfoDao dao = spy(UserInfoDao.class);
        when(dao.select()).thenReturn(new UserInfo());
    }
}
