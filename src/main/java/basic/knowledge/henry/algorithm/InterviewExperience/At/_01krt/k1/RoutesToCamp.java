package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k1;

import java.util.*;

public class RoutesToCamp {
    public static void main(String[] args) {
        String[][] roads = new String[][]{{"Riverport", "Chester", "40"}, {"Chester", "Campground", "60"}, {"Halifax", "Chester", "40"}};
        String[] starts = new String[]{"Riverport", "Halifax"};

        String[][] people = new String[][]{{"Colin", "Riverport"}, {"Sam", "Chester"}, {"Alyssa", "Halifax"}};
        List<List<String>> res = new RoutesToCamp().solution(roads,starts,people);
        System.out.println(res);
//
    }


    private List<List<String>> solution(String[][] roads, String[] starts, String[][] people) {
        //create graph
        HashMap<String,String[]> graph = new HashMap<>();
        for(String[] r : roads){
            String src = r[0];
            String trg = r[1];
            String time = r[2];
            graph.put(src,new String[]{trg,time});
        }

        //create route with accumulative time
        HashMap<String,Place>  route1 = new HashMap<>();
        dfs(graph,starts[0],route1);
        HashMap<String,Place>  route2 = new HashMap<>();
        dfs(graph,starts[1],route2);

        // get result
        List<List<String>> res = new ArrayList<>();
        List<String> subres1 = new ArrayList<>();
        List<String> subres2 = new ArrayList<>();
        for(String[] p : people){
            String placename = p[1];
            String name = p[0];
            if(route1.containsKey(placename) && !route2.containsKey(placename)){
                subres1.add(name);
            }else if(!route1.containsKey(placename) && route2.containsKey(placename)){
                subres2.add(name);
            }else if(route1.containsKey(placename) && route2.containsKey(placename)){
                if(route1.get(placename).time > route2.get(placename).time){
                    subres2.add(name);
                }else {
                    subres1.add(name);
                }
            }
        }

        res.add(subres1);
        res.add(subres2);
        return res;


    }

    /**
     * add cur to route
     * @param graph
     * @param cur
     * @param route
     */
    private void dfs(HashMap<String, String[]> graph, String cur, HashMap<String, Place> route) {
       //cur not existing in the route
        if(!route.containsKey(cur)){
            route.put(cur,new Place(cur,0));
        }


        //cur existing in the route
        String[] next = graph.get(cur);
        if(next == null){
            return;
        }

        Place pl = route.get(cur);
        pl.time += Integer.valueOf(next[1]);
        route.put(cur,pl);

        dfs(graph,next[0],route);
    }


    class Place {
        String name;
        int time;

         public Place(String name, int time) {
             this.name = name;
             this.time = time;
         }
     }
}
