package basic.knowledge.henry.algorithm.InterviewExperience.ms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//这一天太经典了， 地柜时 的visited 不一定就能放for 外面， num++,++num 还是num+ 1 直接影响结果
public class Task5_reverseEdge {

    public static void main(String[] args) {
        List<Integer> from = new ArrayList<>();
        from.add(1);
        from.add(2);
        from.add(3);
        List<Integer> to = new ArrayList<>();
        to.add(4);
        to.add(4);
        to.add(4);
        List<Integer> solution = new Task5_reverseEdge().solution(4, from, to);
        System.out.println(solution);
    }
    HashMap<Integer,List<Integer>> bi = new HashMap<>();
    HashMap<Integer,List<Integer>> uni = new HashMap<>();

    List<Integer> res = new ArrayList<>();
    public List<Integer> solution(int n,List<Integer> from,List<Integer> to){
        int e = n - 1;
        for(int i = 1;i<= n;i++){
            bi.put(i,new ArrayList<>());
            uni.put(i,new ArrayList<>());
        }

        for(int i = 0;i< e;i++){
            int f = from.get(i);
            int t = to.get(i);
            bi.get(f).add(t);
            bi.get(t).add(f);
            uni.get(f).add(t);
        }

        for(int i = 1;i<= n;i++){
            HashSet<Integer> visited = new HashSet<>();
            visited.add(i);// 一定要这么做，因为visited 一定要放在dfs 的for 里面
            dfs(i,visited,n,0);
        }
        return res;
    }
    private void dfs(int cur, HashSet<Integer> visited, int n,int num) {
        if(visited.size() == n){
            res.add(num);
            return;
        }

        for(Integer next : bi.get(cur)){
            if(visited.contains(next)){
                continue;
            }
            visited.add(next);
            if(uni.get(next).contains(cur) && !uni.get(cur).contains(next)){
                dfs(next,visited,n,++num);//num++ num+1 都不对
            }else{
                dfs(next,visited,n,num);
            }
        }
    }
}
