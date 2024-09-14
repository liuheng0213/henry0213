package basic.knowledge.henry.mockTest.course;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListMockTest {
    @Test
    public void mockList() {
        List mockedList  = mock(List.class);//it can mock every function in List

        mockedList.add("one");
        mockedList.clear();

        //通过Mockito的verify来验证是否调用过List的add方法。
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void mockList1() {
        List mockedList  = mock(List.class);

        System.out.println(mockedList.get(0));//null

    }

    @Test
    public void mockList3() {
        List mockedList  = mock(List.class);

        //调用get(0)时，返回first
        when(mockedList.get(0)).thenReturn("first");
        //调用get(1)时，直接抛出异常
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //返回first
        System.out.println(mockedList.get(0));
        //抛出异常
        System.out.println(mockedList.get(1));

        //没有存根，则会返回null
        System.out.println(mockedList.get(999));


    }

    @Test
    public void mockList4() {
        List mockedList  = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

        mockedList.clear();
    }


    @Test
    public void mockList5() {
        List mockedList  = mock(List.class);
        when(mockedList.get(0)).thenReturn(0).thenReturn(1).thenReturn(2);

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(0));
    }


    /**
     * 哈哈哈，被我逮到了吧
     * 参数为:[0]
     * 方法名为:get
     * 结果由我决定
     */
    @Test
    public void mockList6() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                System.out.println("哈哈哈，被我逮到了吧");
                Object[] arguments = invocationOnMock.getArguments();
                System.out.println("参数为:" + Arrays.toString(arguments));
                Method method = invocationOnMock.getMethod();
                System.out.println("方法名为:" + method.getName());

                return "结果由我决定";
            }
        });

        System.out.println(mockedList.get(0));
    }


    /**
     * 如果我想监控这个对象有没有被调用get方法，具体参数是什么我并不关心，该咋办。
     * 这个时候就用到了参数匹配器。
     */
    @Test
    public void mockList7() {
        List mockedList  = mock(List.class);

        when(mockedList.get(0)).thenReturn("first");

        //返回first,这里是个真是的调用
        System.out.println(mockedList.get(0));

        //验证是否调用过get函数。这里的anyInt()就是一个参数匹配器。
        verify(mockedList).get(anyInt());
    }

    @Test
    public void mockList8() {
        List mockedList  = mock(List.class);

        when(mockedList.get(0)).thenReturn("first");

        //返回first
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(0));

        //验证是否被用过get，验证调用1次
        /**
         * org.mockito.exceptions.verification.TooManyActualInvocations:
         * list.get(<any integer>);
         * Wanted 1 time:
         * -> at basic.knowledge.henry.mockTest.course.ListMockTest.mockList8(ListMockTest.java:129)
         * But was 2 times:
         * -> at basic.knowledge.henry.mockTest.course.ListMockTest.mockList8(ListMockTest.java:125)
         * -> at basic.knowledge.henry.mockTest.course.ListMockTest.mockList8(ListMockTest.java:126)
         */
        //verify(mockedList).get(anyInt());



        verify(mockedList, times(2)).get(anyInt());

        //一次也不能调用，等于times(0)
//        verify(mockedList, never()).add("never happened");
//
         //至多、至少
//        verify(mockedList, atMostOnce()).add("once");
//        verify(mockedList, atLeastOnce()).add("three times");
//        verify(mockedList, atLeast(2)).add("three times");
//        verify(mockedList, atMost(5)).add("three times");


    }


    class Student{
        public void sleep(int id, String studNo, String name) {

        }
    }

    @Test
    public void mockStudent() {
        Student student = mock(Student.class);

        student.sleep(1, "1", "admin");

        verify(student).sleep(anyInt(), anyString(), eq("admin"));

    }


    @Test
    public void spyList() {
        //申请了一个真实的对象
        List list = new LinkedList();
        List spy = spy(list);

        //可以选择存根某些函数， 就是不会返回真实结果
        when(spy.size()).thenReturn(100);

        //调用真实的方法
        spy.add("one");
        spy.add("two");

        //打印第一个元素
        System.out.println(spy.get(0));

        //获取list的大小
        System.out.println(spy.size());

        //验证
        verify(spy).add("one");
        verify(spy).add("two");
    }


    /**
     * 当使用spy的时候，有一个很容易掉进去的陷进。
     * 即spy监听的是真实的对象，在操作真实对象的时候可能会出现越界之类的问题。
     */
    @Test
    public void spyList1() {
        List list = new LinkedList();
        List spy = spy(list);


        //通过 要让
//        doReturn("foo").when(spy).get(0);

        //报错 IndexOutOfBoundsException， 因为这个List还是empty
         when(spy.get(0)).thenReturn("foo");
    }









}
