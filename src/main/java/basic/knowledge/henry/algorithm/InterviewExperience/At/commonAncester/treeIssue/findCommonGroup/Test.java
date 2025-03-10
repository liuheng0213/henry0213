package basic.knowledge.henry.algorithm.InterviewExperience.At.commonAncester.treeIssue.findCommonGroup;

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
        Group common1 = test.findCommon2(c, louis, henry);
        System.out.println(common1);
        Group common2 = test.findCommon(c, louis, henry);
        System.out.println(common2);
        //a, louis, jane should return null
        System.out.println(common1 == null ? common2 == null :common1.equals(common2));
    }


    /**
     * 从group 出发找到e1 e2 的最近的祖先, 一定有e1, e2,注意 只需要遍历到group ,employee 不在遍历节点中
     * @param group
     * @param e1
     * @param e2
     * @return
     */
    public Group findCommon2(Group group, Employee e1, Employee e2){
        ReturnType returnType = helper(group, e1, e2);
        if(returnType == null){
            return null;
        }else if(returnType.status != 0){
            return null;
        }else{
            return returnType.group;
        }

    }
    public ReturnType helper(Group group, Employee e1, Employee e2){
        if((group.subGroups.isEmpty() && group.employees.isEmpty())){
           return null;
        }


        ReturnType r1 = null;
        ReturnType r2 = null;

        for(Group g : group.subGroups){
            ReturnType subReturnType = helper(g,e1,e2);
            if(subReturnType == null){
               continue;
            }
           if(subReturnType.status == 1){
               r1 = subReturnType;
           }else if(subReturnType.status == 2){
               r2 = subReturnType;
           }else{
               return subReturnType;
           }
        }


        if(group.employees.contains(e1) || group.employees.contains(e2)){
            if(group.employees.contains(e1) && group.employees.contains(e2)){
                return new ReturnType(0,group);
            }
            if(group.employees.contains(e1)){
                r1 = new ReturnType(1,group);
            }else{
                r2 =  new ReturnType(2,group);
            }

        }
        if(r1 != null && r2 != null){
            return new ReturnType(0,group);
        }

        return r1 != null? r1 : r2;

    }
    private class ReturnType{
        int status;

        Group group;

        public ReturnType(int status, Group group) {
            this.status = status;
            this.group = group;
        }
    }


    boolean found1 = false;
    boolean found2 = false;
    /**
     *
     * @param group
     * @param e1
     * @param e2
     * @return
     */
    public Group findCommon(Group group, Employee e1, Employee e2){
        //because find method might return group which has either e1 or e2. or both of them
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
     *
     * so totally it returns four type response
     * response have both e1 and e2
     * response has e1;
     * response has e2
     * response is null
     *
     * @param group
     * @param e1
     * @param e2
     * @return
     */
    private Group find(Group group, Employee e1, Employee e2){
        if(group.subGroups.isEmpty() && group.employees.isEmpty() || group.subGroups.isEmpty() && !group.employees.contains(e1) && !group.employees.contains(e2) ){
            return null;
        }

        List<Group> gs = new ArrayList<>();
        //group.subGroups 3 种可能
        //1  all  null
        //2  one has e1, another one has e2 ,others are null
        //3  one has e1 and e2
        //需要合适的遍历记录上述状态 it is gs
        //在后序判断使用 如果gs 有两个个element 则状态2 or 3;如果 gs 只有一个 element  状态4 ，如果size = 0 则为 状态1
        for(Group g : group.subGroups){
            Group subGroup = find(g,e1,e2);
            if(subGroup != null){
                gs.add(subGroup);
            }
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

        if(gs.size() == 0){
            return null;
        }else if(gs.size() == 1){
            return gs.get(0);
        }else{
            return group;
        }
    }


}
