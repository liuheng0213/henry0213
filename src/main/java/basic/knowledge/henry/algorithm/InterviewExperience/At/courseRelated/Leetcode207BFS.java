package basic.knowledge.henry.algorithm.InterviewExperience.At.courseRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode207BFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        int[] coursesNoPre = new int[numCourses];
        HashMap<Integer, ArrayList<Integer>> dependRelations = new HashMap<>();

        for(int i = 0;i < prerequisites.length;i++){
            coursesNoPre[prerequisites[i][0]]++;//这么做以后,为0 的就是bfs的起点
            ArrayList<Integer> preList = dependRelations.get(prerequisites[i][1]);
            if(preList == null){
                preList = new ArrayList<>();
                preList.add(prerequisites[i][0]);
                dependRelations.put(prerequisites[i][1],preList);
            }else{
                preList.add(prerequisites[i][0]);
                dependRelations.put(prerequisites[i][1],preList);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i < numCourses;i++){
            if(coursesNoPre[i] == 0){ // it means "i" is the first course to serve as the starting point
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int ele = queue.poll();
            numCourses--;
            ArrayList<Integer> arrayList = dependRelations.get(ele);
            if (arrayList != null) {
                for(Integer course :  arrayList){
                    coursesNoPre[course]--;
                    if(coursesNoPre[course] == 0){ // it means "course" is ready to learn
                        queue.add(course);
                    }
                }
            }
        }
        return numCourses == 0;
    }
}
