package basic.knowledge.henry.algorithm.InterviewExperience.At._03update_1;

import java.util.*;

public class _02ScreenInterview {

    List<String> types = Arrays.asList("1","2","3");
    HashSet<String> mandotory =  new HashSet<>(Arrays.asList("id","type","time"));

    public static void main(String[] args) {
        _02ScreenInterview screenInterview = new _02ScreenInterview();
        List<HashMap<String,String>> msgs = new ArrayList<>();
        HashMap<String, String> msg = new HashMap<>();
        msg.put("id","-1");
        msg.put("type","1");
        msg.put("time","11111111");

        HashMap<String, String> msg1 = new HashMap<>();
        msg.put("id","2");
        msg.put("type","2");
        msg.put("time","111111112122222");

        HashMap<String, String> msg2 = new HashMap<>();
        msg.put("id","2");
        msg.put("type","3");
        msg.put("time","11111111");

        HashMap<String, String> msg3 = new HashMap<>();
        msg.put("id","2");
        msg.put("type","3");
//        msg.put("time","11111111");
        msgs.add(msg);
        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);

        List<HashMap<String, String>> solve = screenInterview.solve(msgs);
        System.out.println(solve);
    }

    public List<HashMap<String,String>> solve(List<HashMap<String,String>> msgs){
        List<HashMap<String,String>> res = new ArrayList<>();
        for(HashMap<String,String> msg : msgs){
            if(isValid(msg)){
                res.add(msg);
            }
        }
        return res;
    }

    private boolean isValid(HashMap<String,String> tran) {
        if(!tran.keySet().containsAll(mandotory)){
           return false;
        }
        for(String key : tran.keySet()){
            String value = tran.get(key);
            if(key.equals("id") && Integer.valueOf(value)<= 0){
                return false;
            }
            if(key.equals("event_type") && !types.contains(value)){
                return false;
            }
            if(key.equals("time") && new Date(Long.valueOf(value)).compareTo(new Date())>0){
                return false;
            }
        }
        return true;
    }
}
