package basic.knowledge.henry.algorithm.InterviewExperience.At.stringGraph;

import java.util.*;

public class Karat1_twice {


    public static void main(String[] argv) {
        String[] songs1 = {
                "Down By the River",
                "River of Dreams",
                "Take me to the River",
                "Dreams",
                "Blues Hand Me Down",
                "Forever Young",
                "American Dreams",
                "All My Love",
                "Cantaloop",
                "Take it All",
                "Love is Forever",
                "Young American",
                "Every Breath You Take"
        };
        String song1_1 = "Every Breath You Take";
        String song1_2 = "Dreams";
        String song1_3 = "Blues Hand Me Down";
        String song1_4 = "Cantaloop";

        String[] songs2 = {
                "Bye Bye Love",
                "Nothing at All",
                "Money for Nothing",
                "Love Me Do",
                "Do You Feel Like We Do",
                "Bye Bye Bye",
                "Do You Believe in Magic",
                "Bye Bye Baby",
                "Baby Ride Easy",
                "Easy Money",
                "All Right Now"
        };
        String song2_1 = "Bye Bye Bye";
        String song2_2 = "Bye Bye Love";

        String[] songs3 = {
                "Love Me Do",
                "Do You Believe In Magic",
                "Magic You Do",
                "Magic Man",
                "Man In The Mirror"
        };
        String song3_1 = "Love Me Do";

        Karat1_twice karat1 = new Karat1_twice();
        List<String> solution = karat1.solution(songs2, song2_1);

        for (String str : solution) {
            System.out.println(str);
        }
    }



    int max = 1;
    List<String> maxList = new ArrayList<>();
    private List<String> solution(String[] words, String start) {
        HashMap<String,List<String>> graph = createGraph(words);
        for(String word : words){
            if(word.equals(start)){
                dfs(start,graph,1,new HashSet<>(),new LinkedList<>());
            }
        }

        return maxList;
    }

    private void dfs(String cur, HashMap<String, List<String>> graph, int depth,HashSet<String> marked,LinkedList<String> tmp) {
        marked.add(cur);
        tmp.add(cur);

        if(depth > max){
            max = depth;
            maxList = new ArrayList<>(tmp);
        }

        if(graph.containsKey(cur)){
            for(String w : graph.get(cur)){
                if(!marked.contains(w)){
                    dfs(w,graph,depth+1,marked,tmp);
                }
            }
        }

        marked.remove(cur);
        tmp.pollLast();

    }


    private HashMap<String, List<String>> createGraph(String[] words) {
        HashMap<String,List<String>> graph =new HashMap<>();
        for(int i =0;i< words.length;i++){
            String key = words[i];
            for(int j =0;j< words.length;j++){
                if(!key.equals(words[j]) &&canlink(key,words[j])){
                    graph.putIfAbsent(key,new ArrayList<>());
                    graph.get(key).add(words[j]);
                }

            }
        }
        return graph;
    }

    private boolean canlink(String key, String word) {
        String[] s = key.split(" ");
        String[] w = word.split(" ");
        return s[s.length - 1].equals(w[0]);
    }


}
