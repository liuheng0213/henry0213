package basic.knowledge.henry.algorithm.InterviewExperience.At.badgeAccess;

import java.util.*;

public class InvalidBadgeAccess {
    public static void main(String[] args) {
        String[][]  badge_records = {
                {"Martha", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Paul", "enter"},
                {"Curtis", "enter"},
                {"Paul", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"}
        };


        InvalidBadgeAccess invalidBadgeAccess = new InvalidBadgeAccess();
        Set<String> valids = invalidBadgeAccess.validate(badge_records);

        for(String name : valids){
            System.out.println(name);
        }
    }

    public Set<String> validate(String[][]  badge_records ){
        HashMap<String, Stack<String>> map = new HashMap<>();

        for(String[] strs : badge_records){
            String name = strs[0];
            String action = strs[1];
            map.putIfAbsent(name,new Stack<>());
            Stack<String> st = map.get(name);

            if(!st.isEmpty()){
                if(st.size() > 1){
                    continue;
                    //following size is 1
                }else if(st.peek().equals("exit")){
                    continue;
                }else{
                    if(action.equals("enter")){
                        st.push(action);
                    }else{
                        st.pop();
                    }
                }
            }else{
                st.push(action);
            }
        }

        Set<String> res = new HashSet<>();
        for(String key : map.keySet()){
            if(!map.get(key).isEmpty()){
                res.add(key);
            }
        }
        return res;
    }
}
