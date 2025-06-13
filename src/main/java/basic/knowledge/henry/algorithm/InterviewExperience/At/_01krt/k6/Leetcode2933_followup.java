package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k6;

import java.util.*;

public class Leetcode2933_followup {
    public HashMap<String,List<Integer>> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String,List<Integer>> nameToAccesses = new HashMap<>();
        for(int i =0;i< access_times.size();i++){
            String name = access_times.get(i).get(0);
            int time = convert(access_times.get(i).get(1));
            List<Integer> times = nameToAccesses.computeIfAbsent(name, k->new ArrayList<>());
            times.add(time);
        }

        HashMap<String,List<Integer>> high_access_name = new HashMap<>();
        for(Map.Entry<String,List<Integer>> entries : nameToAccesses.entrySet()){
            String name = entries.getKey();
            List<Integer> access = entries.getValue();
            Collections.sort(access);
            int i =0;
            int j = 0;
            List<Integer> times = entries.getValue();
            while(j< access.size()){
                Integer ti = access.get(i);
                Integer tj = access.get(j);
                if(tj - ti < 60){
                    j++;
                }else {
                    if(j - i >=3){
                        //populate times
                        for(int k = i; k<= j;k++){
                            times.add(access.get(i));
                        }
                        high_access_name.put(name,times);
                        break;
                    }
                    i++;
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
