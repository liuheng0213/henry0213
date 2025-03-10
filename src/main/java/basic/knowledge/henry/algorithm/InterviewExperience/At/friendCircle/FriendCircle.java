package basic.knowledge.henry.algorithm.InterviewExperience.At.friendCircle;

import basic.knowledge.henry.java8.basicApi.Employee;

import java.util.*;

/**
 * 其实就是看是否所有点联通。 dfs搞定
 */
public class FriendCircle {

    public static void main(String[] args) {
        String[] employee_records = {
                "1,Richard,Engineering",
                "2,Erlich,HR",
                "3,Monica,Business",
                "4,Dinesh,Engineering",
                "6,Carla,Engineering",
                "9,Laurie,Directors"
        };

        String[] friendshipsInput = {
                "1,2",
                "1,3",
                "1,6",
                "2,4",
                "6,9"
        };
        FriendCircle f = new FriendCircle();
        HashMap<Integer, List<Integer>> employeeFriends = f.getEmployeeFriends(employee_records, friendshipsInput);
        for(Integer key : employeeFriends.keySet()){
            System.out.println(key + " : ");
            for(Integer e : employeeFriends.get(key)){
                System.out.println(e);
            }
            System.out.println("-------");
        }

        HashMap<String,int[]> employees = f.categorizeByDept(employee_records,friendshipsInput);

        boolean isFullyConnected = f.isFullyConnected(employee_records,friendshipsInput);
        System.out.println(isFullyConnected);
        System.out.println("=========");
    }
    HashMap<Integer, Employee> id2Employee = new HashMap<>();

    private HashMap<Integer, List<Integer>> getEmployeeFriends(String[] employeeRecords, String[] friendshipsInput){
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(String str : employeeRecords){
            String[] arr = str.split(",");
            int id = Integer.valueOf(arr[0]);
            Employee e = new Employee(arr[1],arr[2]);
            id2Employee.put(id,e);
            graph.put(id,new ArrayList<>());
        }


        for(String friendship : friendshipsInput){
            String[] arr = friendship.split(",");
            int i1 = Integer.valueOf(arr[0]);
            int i2 = Integer.valueOf(arr[1]);
            graph.putIfAbsent(i1,new ArrayList<>());
            graph.get(i1).add(i2);
            graph.putIfAbsent(i2,new ArrayList<>());
            graph.get(i2).add(i1);
        }

;
//        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()){
//            int id = entry.getKey();
//            List<Integer> fids = entry.getValue();
//            List<Employee> friends = new ArrayList<>();
//            for(Integer fid : fids){
//                friends.add(id2Employee.get(fid));
//            }
//            res.put(id,friends);
//        }
        return graph;

    }


    /**
     * 按照department分类，统计每⼀个department⼈数，和这个department有多少⼈有其他department的朋友
     * @param employeeRecords
     * @param friendshipsInput
     * @return
     */
    private HashMap<String,int[]> categorizeByDept(String[] employeeRecords, String[] friendshipsInput) {
        HashMap<Integer, List<Integer>> friendShips = getEmployeeFriends(employeeRecords, friendshipsInput);
        HashMap<String,List<Integer>> deptToIds = new HashMap<>();
        for(int id : id2Employee.keySet()){
            Employee e = id2Employee.get(id);
            deptToIds.putIfAbsent(e.department,new ArrayList<>());
            deptToIds.get(e.department).add(id);
        }
        HashMap<String,int[]> res = new HashMap<>();
        for(String dept : deptToIds.keySet()){
            res.put(dept,new int[2]);
            List<Integer> ids = deptToIds.get(dept);
            res.get(dept)[0]= ids.size();

            HashSet<String> externalDept = new HashSet<>();
            for(int id : ids){
                List<Integer> fids = friendShips.get(id);
                for(int fid: fids){
                    String fdept = id2Employee.get(fid).department;
                    if(!fdept.equals(dept)){
                        externalDept.add(fdept);
                    }
                }
            }
            res.get(dept)[1] = externalDept.size();
        }
        return res;
    }

    private boolean isFullyConnected(String[] employeeRecords, String[] friendshipsInput){
        HashMap<Integer, List<Integer>> friendShips = getEmployeeFriends(employeeRecords, friendshipsInput);
        Set<Integer> keys = friendShips.keySet();
        int src = friendShips.keySet().iterator().next();
        HashSet<Integer> marked = new HashSet<>();
        dfs(src,marked,friendShips);
        return keys.size() == marked.size();
    }

    private void dfs(int cur, HashSet<Integer> marked,HashMap<Integer, List<Integer>> friendShips) {
        if(!marked.contains(cur)){
            marked.add(cur);
            if(friendShips.containsKey(cur)){
                for(int next : friendShips.get(cur)){
                    dfs(next,marked,friendShips);
                }
            }
        }
    }


    class Employee{
        String name;
        String department;

        public Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    '}';
        }
    }


}
