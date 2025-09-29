package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._14commonAncestor;

import java.util.HashSet;

public class Solution {

    public static void main(String[] args) {
        Group Company = new Group("Company");
        Group HR = new Group("HR");
        Group Engg = new Group("Engg");
        Group BE = new Group("BE");
        Group FE = new Group("FE");
        Group Account = new Group("account");
        Group Teller = new Group("teller");
        Group Cfo = new Group("cfo");
        Group Cpa = new Group("cpa");

        Company.subGroups.add(Account);
        Account.subGroups.add(Teller);
        Account.subGroups.add(Cfo);
        Account.subGroups.add(Cpa);
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
        HR.employees.add(vicky);
        Engg.employees.add(vicky);
        Employee louis = new Employee("louis");

        Teller.employees.add(henry);
        Cfo.employees.add(jane);
        Cfo.employees.add(vicky);
        Cpa.employees.add(louis);

        HR.employees.add(Mona);
        HR.employees.add(Springs);

        BE.employees.add(Alice);
        BE.employees.add(Bob);

        FE.employees.add(Lisa);
        FE.employees.add(Marley);

        Solution sol  = new Solution();
        HashSet<Employee> employees = new HashSet<>();
        employees.add(Alice);
        employees.add(vicky);
//        employees.add(Mona);

        Group group = sol.findCommon(Company, employees);
        System.out.println(group);

    }


    /**
     * stores the cloest common group and  employees found in it
     */
    class CloestGroupWithFoundEmployees{
        Group cloestCommonGroup;
        HashSet<Employee> foundEmployees;
    }

    /**
     * searching from start and find cloest common group of employees
     * if it finds all of them,return the cloest common group
     * if it finds part of them or none of,return null;
     * @param start
     * @param employees
     * @return
     */

    private Group findCommon(Group start, HashSet<Employee> employees) {
        CloestGroupWithFoundEmployees cloestGroupWithFoundEmployees = helper(start,employees);
        if(cloestGroupWithFoundEmployees == null || !cloestGroupWithFoundEmployees.foundEmployees.containsAll(employees)){
            return null;
        }

        return cloestGroupWithFoundEmployees.cloestCommonGroup;
    }


    /**
     * searching from group and return CloestGroupWithFoundEmployees
     * which includes the cloest common group of parts of employees and parts of employees themself
     * if find nothing return empty CloestGroupWithFoundEmployees.
     * @param startGroup
     * @param employees
     * @return
     */
    private CloestGroupWithFoundEmployees helper(Group startGroup, HashSet<Employee> employees) {
        CloestGroupWithFoundEmployees result = new CloestGroupWithFoundEmployees();
        if(startGroup.employees == null && startGroup.subGroups == null){
            return result;
        }

        HashSet<Employee> founds = new HashSet<>();
        if(startGroup.subGroups != null){
            for(Group cloestCommonGroup: startGroup.subGroups){
                CloestGroupWithFoundEmployees subCloestGroupWithFoundEmployees = helper(cloestCommonGroup,employees);
                if(findAll(subCloestGroupWithFoundEmployees,employees)){
                    return subCloestGroupWithFoundEmployees;
                }
                for(Employee em: subCloestGroupWithFoundEmployees.foundEmployees){
                    if(employees.contains(em)){
                        founds.add(em);
                    }
                }
            }
        }
        result.cloestCommonGroup = startGroup;
        if(startGroup.employees != null){
            for(Employee em: startGroup.employees){
                if(employees.contains(em)){
                    founds.add(em);
                }
            }
            result.foundEmployees = founds;
        }

        return result;
    }

    private boolean findAll(CloestGroupWithFoundEmployees cloestGroupWithFoundEmployees, HashSet<Employee> employees) {
        return cloestGroupWithFoundEmployees.foundEmployees.containsAll(employees);
    }

}
