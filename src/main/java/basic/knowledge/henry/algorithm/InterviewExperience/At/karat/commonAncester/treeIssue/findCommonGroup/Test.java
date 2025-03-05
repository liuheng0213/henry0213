package basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester.treeIssue.findCommonGroup;

import basic.knowledge.henry.algorithm.InterviewExperience.At.karat.commonAncester.treeIssue.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Group h = new Group("h");
        Group a = new Group("a");
        Group b = new Group("b");
        Group c = new Group("c");
        Group d = new Group("d");
        Group g = new Group("g");
        b.subGroups.add(c);
        h.subGroups.add(a);
        h.subGroups.add(b);
        g.subGroups.add(d);
        g.subGroups.add(h);

        Employee vicky = new Employee("vicky");
        Employee jane = new Employee("jane");
        Employee henry = new Employee("henry");
        Employee louis = new Employee("louis");
        Employee ayumi = new Employee("ayumi");
        a.employees.add(vicky);
        a.employees.add(jane);
        b.employees.add(henry);
        c.employees.add(louis);
        d.employees.add(ayumi);

        Test test = new Test();
        Group common = test.findCommon(h, jane, vicky);
        //a, louis, jane should return null
        System.out.println(common);
    }

    boolean found1 = false;
    boolean found2 = false;
    public Group findCommon(Group group, Employee e1, Employee e2){
        //because find method might return group which has e1 or e2.
        //so we have to make sure found1 && found2 when it should return the ancestor
        Group res = find(group,  e1, e2);
        if (found1 && found2) {
            return res;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return null;
    }

    /**
     * definition: find returns : after traverse post order, if group itself has e1 or e2, return this group,
     * otherwise return the closest ancestor of e1 and e2
     * @param group
     * @param e1
     * @param e2
     * @return
     */
    private Group find(Group group, Employee e1, Employee e2){
        if(group.subGroups.isEmpty() && group.employees.isEmpty() || group.subGroups.isEmpty() && !group.employees.contains(e1) && !group.employees.contains(e2) ){
            return null;
        }

        List<Group> groups = new ArrayList<>();
        for(Group g : group.subGroups){
            Group subGroup = find(g,e1,e2);
            groups.add(subGroup);
        }

        if(group.employees.contains(e1) || group.employees.contains(e2)){
            if(group.employees.contains(e1)){
                found1 = true;
            }
            if(group.employees.contains(e2)){
                found2 = true;
            }
            return group;
        }

        boolean allNotNUlL = true;
        Group notNull = null;
        for(Group g: groups){
            if(g != null){
                notNull = g;
            }else{
                allNotNUlL = false;
            }
        }

        if(allNotNUlL){
            return group;
        }else{
            return notNull;
        }
    }
}
