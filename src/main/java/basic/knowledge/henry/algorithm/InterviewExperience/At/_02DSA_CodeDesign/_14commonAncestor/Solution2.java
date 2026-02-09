package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._14commonAncestor;

import java.util.HashSet;

public class Solution2 {

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

        Solution2 sol  = new Solution2();
        HashSet<Employee> employees = new HashSet<>();
        employees.add(jane);
        employees.add(vicky);
        employees.add(louis);
//        employees.add(Mona);

        Group group = sol.findCommon(Company, employees);
        System.out.println(group);

    }

    private Group findCommon(Group group, HashSet<Employee> employeesNeedToFind) {
        ClosestCommonGroupAndFoundEmployeesInIt cloestCommonGroupWithFoundEmployees = helper(group,employeesNeedToFind);
        if(cloestCommonGroupWithFoundEmployees == null || cloestCommonGroupWithFoundEmployees.employees.isEmpty()|| !findAll(cloestCommonGroupWithFoundEmployees.employees,employeesNeedToFind)){
            return null;
        }

        return cloestCommonGroupWithFoundEmployees.group;
    }

    private boolean findAll(HashSet<Employee> employees, HashSet<Employee> employeesNeedToFind) {
        return employees.containsAll(employeesNeedToFind);
    }

    private ClosestCommonGroupAndFoundEmployeesInIt helper(Group group, HashSet<Employee> employeesNeedToFind) {
        if(group.employees.isEmpty() && group.subGroups.isEmpty()){
            return new ClosestCommonGroupAndFoundEmployeesInIt();
        }
        ClosestCommonGroupAndFoundEmployeesInIt closestCommonGroupAndFoundEmployeesInIt = new ClosestCommonGroupAndFoundEmployeesInIt();

        HashSet<Employee> found = new HashSet<>();
        if(!group.subGroups.isEmpty()){
            for(Group subGroup : group.subGroups){
                ClosestCommonGroupAndFoundEmployeesInIt groupAndFoundEmployeesInIt = helper(subGroup,employeesNeedToFind);
                if(findAll(groupAndFoundEmployeesInIt.employees,employeesNeedToFind)){
                    return groupAndFoundEmployeesInIt;
                }
                found.addAll(groupAndFoundEmployeesInIt.employees);
            }
        }


        closestCommonGroupAndFoundEmployeesInIt.group = group;
        if(!group.employees.isEmpty()){
            for(Employee e : group.employees){
                if(employeesNeedToFind.contains(e)){
                    found.add(e);
                }
            }
        }
        //must be outside of  if(!group.employees.isEmpty())
        closestCommonGroupAndFoundEmployeesInIt.employees = found;
        return closestCommonGroupAndFoundEmployeesInIt;
    }


    class ClosestCommonGroupAndFoundEmployeesInIt{
        Group group;
        HashSet<Employee> employees;

        public ClosestCommonGroupAndFoundEmployeesInIt() {
            this.group = null;
            this.employees = new HashSet<>();
        }
    }
}
