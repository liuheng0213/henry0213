package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;



import basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._05RouteService.RouterService;
import org.junit.Assert;
import org.junit.Test;

public class TestRouteService {

    @Test
    public void test(){
        RouterService routeService = new RouterService();
        Assert.assertEquals(true,routeService.adddrequest("/bar", 2));
        Assert.assertEquals(true,routeService.adddrequest("/bar/qu", 2));
        Assert.assertEquals(false,routeService.adddrequest("/c/d", 5));//false
        Assert.assertEquals(true,routeService.adddrequest("/bar/qu/ert", 1));
        Assert.assertEquals(true,routeService.adddrequest("/bar/qu/ert/ssh", 2)); // true
        Assert.assertEquals(false,routeService.adddrequest("/bar/qu/ert", 3)); // false (path already exists)
        Assert.assertEquals(true,routeService.adddrequest("/bar/qu/xyz", 4)); // true
        Assert.assertEquals(true,routeService.adddrequest("/bar/qu/xyz/henry", 4)); // true
        Assert.assertEquals(1,routeService.callrequest("/bar/qu/ert")); // 1
        Assert.assertEquals(1,routeService.callrequest("/bar/*/ert")); // 1
        Assert.assertEquals(2,routeService.callrequest("/bar/*/*/ssh")); //2 (using wildcard)
        Assert.assertEquals(4,routeService.callrequest("/bar/*/*/henry")); //4 (using wildcard)
        Assert.assertEquals(2,routeService.callrequest("/bar/*/*/*")); //2 (using wildcard)
        Assert.assertEquals(4,routeService.callrequest("/bar/qu/xyz")); // 4
        Assert.assertEquals(4,routeService.callrequest("/bar/*/xyz")); // 4
        Assert.assertEquals(2,routeService.callrequest("/bar/qu")); // 2 (path doesn't exist)
        Assert.assertEquals(-1,routeService.callrequest("/")); // -1
    }
}
