package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._14commonAncestor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Group Company = new Group("Company");
        Group HR = new Group("HR");
        Group Engg = new Group("Engg");
        Group BE = new Group("BE");
        Group FE = new Group("FE");
        Group account = new Group("account");
        Group teller = new Group("teller");
        Group cfo = new Group("cfo");
        Group cpa = new Group("cpa");

        Company.subGroups.add(account);
        account.subGroups.add(teller);
        account.subGroups.add(cfo);
        account.subGroups.add(cpa);
        Company.subGroups.add(HR);
        Company.subGroups.add(Engg);
        Engg.subGroups.add(BE);
        Engg.subGroups.add(FE);

        Employee Mona = new Employee("Mona");
        Employee Springs = new Employee("Springs");
        Employee Alice = new Employee("Alice");
        Employee Bob = new Employee("Bob");
        Employee Lisa = new Employee("Lisa");
        Employee Marley = new Employee("Marley");


        Employee henry = new Employee("henry");
        Employee jane = new Employee("jane");
        Employee vicky = new Employee("vicky");
        Employee louis = new Employee("louis");

        teller.employees.add(henry);
        cfo.employees.add(jane);
        cfo.employees.add(vicky);
        cpa.employees.add(louis);

        HR.employees.add(Mona);
        HR.employees.add(Springs);

        BE.employees.add(Alice);
        BE.employees.add(Bob);

        FE.employees.add(Lisa);
        FE.employees.add(Marley);

        Solution solution = new Solution();
        HashSet<Employee> employees = new HashSet<>();
        employees.add(Bob);
        employees.add(Alice);
//        employees.add(Alice);
//        employees.add(Springs);
//        employees.add(Mona);

        Group group = solution.findCommon(Company, employees);
        System.out.println(group);
    }

    private Group findCommon(Group engg, HashSet<Employee> employees) {
        GroupWithFoundEmployees gwfe = helper(engg,employees);
        if(gwfe == null){
            return null;
        }
        if(gwfe.es.containsAll(employees)){
            return gwfe.group;
        }
        return null;

    }

    /**
     * FIND THE CLOSEST common Group With Found Employees which existing in input employees
     *
     * @param group
     * @param employees
     * @return
     */
    private GroupWithFoundEmployees helper(Group group, HashSet<Employee> employees) {
        if(group.subGroups.isEmpty() && group.employees.isEmpty()){
            return null;
        }

        GroupWithFoundEmployees cg = new GroupWithFoundEmployees(group);
        boolean flag = false;
        if(!group.employees.isEmpty()){
            for(Employee e: group.employees){
                if(employees.contains(e)){
                    flag = true;
                    cg.es.add(e);
                }
            }
        }
        // group has direct employee, it is the closest group, return immediately.
        if(flag){
            return cg;
        }

        List<GroupWithFoundEmployees> closerGroups = new ArrayList<>();
        if(!group.subGroups.isEmpty()){
            for(Group g : group.subGroups){
                GroupWithFoundEmployees subGroups = helper(g,employees);//subGroups might be null
                closerGroups.add(subGroups);
            }
        }


        int num = 0;
        Group oneGroup = null;
        HashSet<Employee> founds = new HashSet<>();
        for(GroupWithFoundEmployees g: closerGroups){
            if(g != null){
                num++;
                oneGroup = g.group;
                founds.addAll(g.es);
            }
        }


        if(num == 0){
            return null;
        }else if(num == 1){
            cg.group = oneGroup;
            cg.es = founds;
            return cg;
        }else{
            cg.group = group;
            cg.es = founds;
            return cg;
        }

    }

    class GroupWithFoundEmployees{
        Group group;
        HashSet<Employee> es;

        public GroupWithFoundEmployees(Group group) {
            this.group = group;
            this.es = new HashSet<>();
        }
    }

}
