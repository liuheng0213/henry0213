package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.k1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class GoToCamp {
    public static void main(String[] args) {
        String[][] roads = new String[][]{{"Riverport", "Chester", "40"}, {"Chester", "Campground", "60"}, {"Halifax", "Chester", "40"}};
        String[] starts = new String[]{"Riverport", "Halifax"};

        String[][] people = new String[][]{{"Colin", "Riverport"}, {"Sam", "Chester"}, {"Alyssa", "Halifax"}};
        List<List<String>> res = new GoToCamp().solution(roads,starts,people);

        System.out.println(res);
    }

    List<List<People>> routes;
    private List<List<String>> solution(String[][] roads, String[] starts, String[][] people) {
        routes = new ArrayList<>();
        HashMap<String,List<String[]>> graph = new HashMap<>();
        for(String[] r : roads){
            graph.putIfAbsent(r[0],new ArrayList<>());
            graph.get(r[0]).add(new String[]{r[1],r[2]});
        }

        HashMap<String,String> placeToPeople = new HashMap<>();
        for(String[] p : people){
            placeToPeople.put(p[1],p[0]);
        }


        List<People>[] possibleRoutes = new ArrayList[starts.length];
        for(int i =0;i< starts.length;i++){
            dfs(starts[i],0,graph,new ArrayList<>(),placeToPeople);
//            possibleRoutes[i] = routes;
            routes = new ArrayList<>();
        }
        //compare
        List<List<String>> res = new ArrayList<>();
        // onlu two cars
        List<People> first =   possibleRoutes[0];
        List<People> second =   possibleRoutes[1];




        return null;


    }

    private void dfs(String cur, int dis,HashMap<String, List<String[]>> graph, List<People> route, HashMap<String, String> placeToPeople) {
        if(placeToPeople.containsKey(cur)){
            route.add(new People(placeToPeople.get(cur),dis));
        }

        if(cur.equals("Campground") || !graph.containsKey(cur)){
            routes.add(new ArrayList<>(route));
            return;
        }


        if(graph.containsKey(cur)){
            for(String[] nextNode : graph.get(cur)){
                String next = nextNode[0];
                String nextDis = nextNode[1];
                dfs(next,dis+Integer.valueOf(nextDis),graph,route,placeToPeople);
            }
        }
        route.remove(route.size() - 1);
    }

    class People {
        String name;
        int time;

         public People(String name, int time) {
             this.name = name;
             this.time = time;
         }
     }
}
