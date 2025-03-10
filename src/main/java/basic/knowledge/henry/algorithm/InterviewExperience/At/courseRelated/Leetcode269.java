package basic.knowledge.henry.algorithm.InterviewExperience.At.courseRelated;

import java.util.*;


/**
 * Can someone explain these test cases ? These are leetcode test cases for the problem.
 * Case-1:
 * ["abc","ab"]
 * Expected : ""
 *
 * Case-2:
 * ["wrt","wrtkj"]
 * Expected : "jkrtw"
 *
 *
 * Case 1 is not lexicographically sorted since both words have the same prefix,
 * but the shorter one comes second. So this is invalid and we have to return "".
 *
 * Case 2 is lexicographically sorted so proceed as normal.
 * We can't tell anything about the ordering here so we can return any string which has all of the characters.
 *
 */
public class Leetcode269 {
    public static void main(String[] args) {
        String[] words = new String[]{"abc","ab"};
        Leetcode269 leetcode269 = new Leetcode269();
        String s = leetcode269.alienOrder(words);
        System.out.println(s);
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        String result="";
        if(words==null || words.length==0) return result;
        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }
        for(int i=0; i<words.length-1; i++){
            String cur=words[i];
            String next=words[i+1];
            if(notLexicographically(cur,next)){
                return "";
            }
            int length=Math.min(cur.length(), next.length());
            for(int j=0; j<length; j++){
                char c1=cur.charAt(j);
                char c2=next.charAt(j);
                if(c1!=c2){
                    Set<Character> set = new HashSet<>();
                    if(graph.containsKey(c1)) set=graph.get(c1);
                    if(!set.contains(c2)){
                        set.add(c2);
                        graph.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q=new LinkedList<>();
        for(char c: degree.keySet()){
            if(degree.get(c)==0) q.add(c);
        }
        while(!q.isEmpty()){
            char c=q.remove();
            result+=c;
            if(graph.containsKey(c)){
                for(char c2: graph.get(c)){
                    degree.put(c2,degree.get(c2)-1);
                    if(degree.get(c2)==0) q.add(c2);
                }
            }
        }
        if(result.length()!=degree.size()) return "";
        return result;
    }

    private boolean notLexicographically(String cur, String next) {
        int i = 0;
        int j = 0;

        while(i< cur.length() && j < next.length()){
            char a  = cur.charAt(i);
            char b  = next.charAt(j);

            if(a != b){
                return false;
            }
            i++;
            j++;
        }

        if(j == next.length() && i < cur.length()){
            return true;
        }
        return false;

    }
}
