package basic.knowledge.henry.netCode.rpcDemo;

public class AobingServiceImpl implements AobingService {
    @Override
    public String hello(String name) {
        return "Yo man Hello，I am" + name;
    }
}
