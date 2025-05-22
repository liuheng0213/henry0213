package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;



import org.junit.Assert;
import org.junit.Test;

public class TestRouteService {

    @Test
    public void test(){
        RouteService routeService = new RouteService();
        Assert.assertEquals(true,routeService.createPath("/bar", 2));
        Assert.assertEquals(true,routeService.createPath("/bar/qu", 2));
        Assert.assertEquals(false,routeService.createPath("/c/d", 5));//false
        Assert.assertEquals(true,routeService.createPath("/bar/qu/ert", 1));
        Assert.assertEquals(true,routeService.createPath("/bar/qu/ert/ssh", 2)); // true
        Assert.assertEquals(false,routeService.createPath("/bar/qu/ert", 3)); // false (path already exists)
        Assert.assertEquals(true,routeService.createPath("/bar/qu/xyz", 4)); // true
        Assert.assertEquals(true,routeService.createPath("/bar/qu/xyz/henry", 4)); // true
        Assert.assertEquals(1,routeService.get("/bar/qu/ert")); // 1
        Assert.assertEquals(1,routeService.get("/bar/*/ert")); // 1
        Assert.assertEquals(2,routeService.get("/bar/*/*/ssh")); //2 (using wildcard)
        Assert.assertEquals(4,routeService.get("/bar/*/*/henry")); //4 (using wildcard)
        Assert.assertEquals(2,routeService.get("/bar/*/*/*")); //2 (using wildcard)
        Assert.assertEquals(4,routeService.get("/bar/qu/xyz")); // 4
        Assert.assertEquals(4,routeService.get("/bar/*/xyz")); // 4
        Assert.assertEquals(2,routeService.get("/bar/qu")); // 2 (path doesn't exist)
        Assert.assertEquals(-1,routeService.get("/")); // -1
    }
}
