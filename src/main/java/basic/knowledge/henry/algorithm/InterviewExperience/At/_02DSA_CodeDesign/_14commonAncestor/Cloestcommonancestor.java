package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._14commonAncestor;

import java.util.ArrayList;
import java.util.List;


public class Cloestcommonancestor {
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

        Cloestcommonancestor test = new Cloestcommonancestor();
       Group common1 = test.findCommon2(g, louis, ayumi);
        System.out.println(common1);
        Group common2 = test.findCommon(g, louis, ayumi);
        System.out.println(common2);
        //a, louis, jane should return null
        System.out.println(common1 == null ? common2 == null :common1.equals(common2));
    }

    private Group findCommon(Group a, Employee e1, Employee e2) {
        Group group = find(a,e1,e2);
        if(found1 && found2){
            return group;
        }
        return null;
    }

    boolean found1 = false;
    boolean found2 = false;

    /**
     * 三种可能
     * 找到e1 的祖先
     * 找到e2 的祖先
     * 找到 null
     * @param group
     * @param e1
     * @param e2
     * @return
     */
    private Group find(Group group, Employee e1, Employee e2) {
        if(group.employees.isEmpty() && group.subGroups.isEmpty()){
            return null;
        }


        List<Group> gs = new ArrayList<>();
        for(Group subGroup : group.subGroups){
           Group g = find(subGroup,e1,e2);
           if(g != null){ // 找到任一个的 group  加入gs
               gs.add(g);
           }
        }
        if(group.employees.contains(e1) || group.employees.contains(e2)){
            if(group.employees.contains(e1)) {
                found1 = true;
            }

            if(group.employees.contains(e2)){
                found2 =true;
            }
            return group;
        }

        if(gs.size() == 0){
            return null;
        }else if(gs.size() == 1){
            return gs.get(0);
        }else{
            return group;
        }
    }

    private Group findCommon2(Group group, Employee e1, Employee e2) {
        ReturnType returnType = helper(group,e1,e2);
        if(returnType.status == 0){
            return returnType.group;
        }else{
            return null;
        }
    }

    /**
     * 这个无所谓前序后序
     * @param group
     * @param e1
     * @param e2
     * @return
     */
    private ReturnType helper(Group group, Employee e1, Employee e2) {
        if(group.subGroups.isEmpty() && group.employees.isEmpty()){
            return new ReturnType(-1,null);
        }
        ReturnType g1 = null;
        ReturnType g2 = null;

        if(!group.subGroups.isEmpty()){
            for(Group subGroup : group.subGroups){
                ReturnType returnType = helper(subGroup,e1,e2);
                if(returnType.status == 1){
                    g1 = returnType;
                }else if(returnType.status == 2){
                    g2 = returnType;
                }else if(returnType.status == 0){
                    return returnType;
                }
            }
        }


        if(!group.employees.isEmpty()){
            if(group.employees.contains(e1) && group.employees.contains(e2)){
                return new ReturnType(0,group);
            }else if(group.employees.contains(e1)){
                g1 =  new ReturnType(1,group);
            }else if(group.employees.contains(e2)){
                g2 = new ReturnType(2,group);
            }
        }


        if(g1 != null && g2 != null){
            return  new ReturnType(0,group);
        }else if(g1 != null){
            return g1;
        }else if(g2 != null){
            return  g2;
        }else{
            return new ReturnType(-1,null);
        }

    }

    class ReturnType{
        int status; //-1 nothing,  0 two employees, 1 employee 1, 2 employee 2

        Group group;


        public ReturnType(int status, Group group) {
            this.status = status;
            this.group = group;
        }
    }
}
