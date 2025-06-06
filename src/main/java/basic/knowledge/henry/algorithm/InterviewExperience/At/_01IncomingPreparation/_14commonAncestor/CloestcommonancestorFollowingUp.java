package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._14commonAncestor;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * o(n+ m) n是group数量，m 是employee 数量
 *
 * 空间复杂度
 */
public class CloestcommonancestorFollowingUp {
    public static void main(String[] args) {
        Group company = new Group("Company");
        Group HR = new Group("HR");
        Group Engg = new Group("Engg");
        Group BE = new Group("BE");
        Group FE = new Group("FE");
        Group account = new Group("account");
        Group teller = new Group("teller");
        Group cfo = new Group("cfo");
        Group cpa = new Group("cpa");

        company.subGroups.add(account);
        account.subGroups.add(teller);
        account.subGroups.add(cfo);
        account.subGroups.add(cpa);
        company.subGroups.add(HR);
        company.subGroups.add(Engg);
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

        CloestcommonancestorFollowingUp multipleEmployees = new CloestcommonancestorFollowingUp();
        HashSet<Employee> employees = new HashSet<>();
        employees.add(Springs);
        employees.add(Alice);
//        employees.add(Mona);

        Group group = multipleEmployees.findCommon(Engg, employees);
        System.out.println(group);

    }

    private Group findCommon(Group g, HashSet<Employee> es) {
        Group res = helper(g,es);
        if(founds.size() == es.size()){
            return res;
        }

        return null;
    }
    HashSet<Employee> founds = new HashSet<>();
    private Group helper(Group g, HashSet<Employee> es) {
        if(g.subGroups.isEmpty() && g.employees.isEmpty()){
            return null;
        }

        List<Group> subGroups = new ArrayList<>();
        if(!g.subGroups.isEmpty()){
            for(Group group : g.subGroups){
                subGroups.add(helper(group,es));
            }
        }

        boolean flag = false;
        if(!g.employees.isEmpty()){
            for(Employee employee: g.employees){
                if(es.contains(employee)){
                    founds.add(employee);
                    flag = true;
                }
            }
        }

       if(flag){
           return g;
       }

        if(subGroups.size() == 0){
            return null;
        }

        int nullNum = 0;
        Group onlyOneNotNUll = null;
        for(Group group : subGroups){
            if(group == null){
                nullNum++;
            }else{
                onlyOneNotNUll = group;
            }
        }

        if(nullNum == subGroups.size()){
            return null;
        }
        if(nullNum == subGroups.size() - 1){
            return onlyOneNotNUll;
        }
        return g;

    }

}
