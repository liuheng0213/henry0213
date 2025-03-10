package basic.knowledge.henry.algorithm.InterviewExperience.At.badgeAccess;

import java.util.*;

public class Leetcode2933 {
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String,List<Integer>> nameToAccesses = new HashMap<>();
        for(int i =0;i< access_times.size();i++){
            String name = access_times.get(i).get(0);
            int time = convert(access_times.get(i).get(1));
            List<Integer> times = nameToAccesses.computeIfAbsent(name, k->new ArrayList<>());
            times.add(time);
        }

        List<String> high_access_name = new ArrayList<>();
        for(Map.Entry<String,List<Integer>> entries : nameToAccesses.entrySet()){
            String name = entries.getKey();
            List<Integer> access = entries.getValue();
            Collections.sort(access);
            for(int i =0;i< access.size() - 2;i++){
                int a1 = access.get(i);
                int a2 = access.get(i+1);
                int a3 = access.get(i+2);
                if(a3 -a1<60 ){
                    high_access_name.add(name);
                    break;
                }
            }
        }
        return high_access_name;
    }

    private int convert(String time){
        int hour = Integer.valueOf(time.substring(0,2));
        int minutes = Integer.valueOf(time.substring(2));
        return hour * 60 + minutes;

    }
}
