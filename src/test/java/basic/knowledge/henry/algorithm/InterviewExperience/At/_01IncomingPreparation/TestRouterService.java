package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._05RouteService.RouterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRouterService {

    RouterService routerService;

    @Before
    public void setup(){
        routerService = new RouterService();
        boolean adddrequest = routerService.adddrequest("/bar", 2);//true
        boolean adddrequest1 = routerService.adddrequest("/bar/qu", 2);//true
        boolean adddrequest2 = routerService.adddrequest("/c/d", 5);//false 必须一步步的加
        System.out.println(routerService.adddrequest("/bar/qu/ert", 1)); // true
        System.out.println(routerService.adddrequest("/bar/qu/ert/ssh", 2)); // true
        System.out.println(routerService.adddrequest("/bar/qu/ert", 3)); // false (path already exists)
        System.out.println(routerService.adddrequest("/bar/qu/xyz", 4)); // true
        System.out.println(routerService.adddrequest("/bar/qu/xyz/henry", 4)); // true
    }

    @Test
    public void testcallrequest(){
        Assert.assertEquals(routerService.callrequest("/bar/qu/ert"),1); // 1
        Assert.assertEquals(1,routerService.callrequest("/bar/*/ert")); // 1
        Assert.assertEquals(routerService.callrequest("/bar/*/*/ssh"),2); //2 (using wildcard)
        Assert.assertEquals(routerService.callrequest("/bar/*/*/henry"),4); //4 (using wildcard)
        Assert.assertEquals(routerService.callrequest("/bar/*/*/*"),2); //2 (using wildcard)
        Assert.assertEquals(routerService.callrequest("/bar/qu/xyz"),4); // 4
        Assert.assertEquals(routerService.callrequest("/bar/*/xyz"),4); // 4
        Assert.assertEquals(routerService.callrequest("/bar/qu"),2); // 2 (path doesn't exist)
        Assert.assertEquals(routerService.callrequest("/"),-1); // -1
    }
}
