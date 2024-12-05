package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.k1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DepPro {
    public static void main(String[] args) {
        String[][] products = new String[][]{
                {"Cheese", "Dairy"},
                {"Carrots", "Produce"},
                {"Potatoes", "Produce"},
                {"Canned Tuna", "Pantry"},
                {"Romaine Lettuce", "Produce"},
                {"Chocolate Milk", "Dairy"},
                {"Flour", "Pantry"},
                {"Iceberg Lettuce", "Produce"},
                {"Coffee", "Pantry"},
                {"Pasta", "Pantry"},
                {"Milk", "Dairy"},
                {"Blueberries", "Produce"},
                {"Pasta Sauce", "Pantry"}
        };

        HashSet<String> bought = new HashSet<>(Arrays.asList("Milk", "Flour", "Chocolate Milk", "Pasta Sauce"));

        int solution = new DepPro().solution(products, bought);
        System.out.println(solution);
    }


    public int solution(String[][] products, HashSet<String> bought){
//        HashMap<String, HashSet<String>> depToProd = new HashMap<>();
        HashMap<String, String> prodToDep = new HashMap<>();

        for(String[] p : products){
//            depToProd.putIfAbsent(p[1],new HashSet<>());
//            depToProd.get(p[1]).add(p[0]);
            prodToDep.put(p[0],p[1]);
        }

        int visits = 0;
        HashSet<String> deps = new HashSet<>();
        String preDept = null;
        for(String b : bought){
            String dep = prodToDep.get(b);
            if(preDept == null){
                visits++;
            }else{
               if(!preDept.equals(dep)){
                   visits++;
               }
            }

            preDept = dep;
            deps.add(dep);
        }

        return visits - deps.size();

    }
}
