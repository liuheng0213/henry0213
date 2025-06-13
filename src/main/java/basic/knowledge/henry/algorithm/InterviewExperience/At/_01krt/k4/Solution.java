package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k4;


import java.util.*;

/**
 * Input Structure:
 *
 * We have two inputs: a list of available parts and a list of required parts for each robot.
 * Each robot's required parts are stored in a dictionary where the key is the robot name and the value is a list of parts needed.
 * Output:
 *
 * We need to return a list of robot names that can be fully constructed with the available parts.
 *
 * List = [
 * "Rosie_claw",
 * "Rosie_sensors",
 * "Dustie_case",
 * "Optimus_sensors",
 * "Rust_sensors",
 * "Rosie_case",
 * "Rust_case",
 * "Optimus_speaker",
 * "Rosie_wheels",
 * "Dustie_case",
 * "Dustie_arms",
 * "Rust_claw",
 * "Dustie_case",
 * "Dustie_speaker",
 * "Optimus_case",
 * "Optimus_wheels",
 * "Optimus_wheels",
 * "Rust_legs",
 * "Optimus_sensors"
 * ]
 * Available Parts = {"sensors", "case", "speaker", "wheels"}
 *
 * output - [Optimus]
 * there were couple of other use cases
 *
 *
 *
 */
public class Solution {
    static String[] a1= new String[]{
            "Rosie_claw",
            "Rosie_sensors",
        "Dustie_case",
        "Optimus_sensors",
        "Rust_sensors",
        "Rosie_case",
        "Rust_case",
        "Optimus_speaker",
        "Rosie_wheels",
        "Dustie_case",
        "Dustie_arms",
        "Rust_claw",
        "Dustie_case",
        "Dustie_speaker",
        "Optimus_case",
        "Optimus_wheels",
        "Optimus_wheels",
        "Rust_legs",
        "Optimus_sensors"
        };

    static String[] a2 = new String[]{"sensors", "case", "speaker", "wheels"};


    public static void main(String[] args) {

        List<String> requiredParts = Arrays.asList(a1);
        List<String> availableParts = Arrays.asList(a2);
        List<String> robots = new Solution().getRobots(requiredParts, availableParts);
        System.out.println(robots);
    }
    HashMap<String, HashSet<String>> nameToParts = new HashMap<>();
    public List<String> getRobots(List<String> requiredParts,List<String> availableParts){
        for(String rp : requiredParts){
            String[] strs = rp.split("_");
            HashSet ava = nameToParts.computeIfAbsent(strs[0],k->new HashSet<>());
            ava.add(strs[1]);
        }


        HashSet<String> avPArts = new HashSet<>(availableParts);
        List<String> res = new ArrayList<>();
        for(String name : nameToParts.keySet()){
            HashSet<String> parts = nameToParts.get(name);
            if(avPArts.containsAll(parts)){
                res.add(name);
            }
        }

        return res;
    }
}
