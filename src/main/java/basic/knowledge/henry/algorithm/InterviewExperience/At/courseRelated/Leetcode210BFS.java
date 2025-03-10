package basic.knowledge.henry.algorithm.InterviewExperience.At.courseRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Leetcode210BFS {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indgrees = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i =0;i<prerequisites.length;i++ ){
            indgrees[prerequisites[i][0]]++;
            if(!map.containsKey(prerequisites[i][1])){
                List<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][0]);
                map.put(prerequisites[i][1],list);
            }else{
                map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i =0;i < numCourses;i++){
            if(indgrees[i] == 0){
                queue.addLast(i);
            }
        }
        int[] res = new int[numCourses];
        int index = 0;
        int num = 0;
        while(!queue.isEmpty()){
            int poll = queue.poll();
            res[index++] = poll;
            if(map.containsKey(poll)){
                for(Integer course : map.get(poll)){
                    indgrees[course]--;
                    if(indgrees[course] == 0){
                        queue.addLast(course);
                    }
                }
            }
            num++;
        }
        if(num != numCourses){
            res = new int[0];
        }
        return  res;
    }
}
