package basic.knowledge.henry.algorithm.InterviewExperience.At.treeIssue;

public class Test {
    public static void main(String[] args) {
        Group h = new Group("h");
        Group a = new Group("a");
        Group b = new Group("b");
        Group c = new Group("c");
        b.subGroups.add(c);
        h.subGroups.add(a);
        h.subGroups.add(b);

        Employee vicky = new Employee("vicky");
        Employee jane = new Employee("jane");
        Employee henry = new Employee("henry");
        Employee louis = new Employee("louis");
        a.employees.add(vicky);
        a.employees.add(jane);
        b.employees.add(henry);
        c.employees.add(louis);

        Test test = new Test();
        Group common = test.findCommon(b, louis, henry);

        System.out.println(common);
    }
    public Group findCommon(Group group,Employee e1,Employee e2){
        boolean found1 = helper(group,e1);
        boolean found2 = helper(group,e2);
        Group res = found1 &&found2? group: null;
        for(Group subGroup: group.subGroups){
             found1 = helper(subGroup,e1);
             found2 = helper(subGroup,e2);
             res = found1 && found2 ?subGroup :res;
        }

        return  res;
    }

    private boolean helper(Group group, Employee e) {
        boolean found = false;
        if(group.subGroups.size() != 0){
            for(Group sub : group.subGroups){
                found = found || helper(sub,e);
            }
        }

        if(group.employees.size() != 0){
            for(Employee employee : group.employees){
                if(employee.equals(e)){
                    found = true;
                    return found;
                }
            }
        }

        return found;

    }
}
