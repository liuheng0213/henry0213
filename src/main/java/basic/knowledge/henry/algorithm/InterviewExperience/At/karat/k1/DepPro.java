package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.k1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

        List<String> bought = Arrays.asList("Milk", "Flour", "Chocolate Milk", "Pasta Sauce");

        int solution = new DepPro().solution(products, bought);
        System.out.println(solution);
    }


    public int solution(String[][] products, List<String> bought){

        HashMap<String, String> prodToDep = new HashMap<>();

        for(String[] p : products){
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
