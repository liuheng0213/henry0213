package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import java.util.HashMap;

public class RouteService {

    Trie root;

    public RouteService() {
        this.root = new Trie();
    }

    public boolean createPath(String path, int content) {
        Trie cur = root;
        String[] strs = path.split("/");
        for(int i =1;i< strs.length;i++){
            String p = strs[i];
            if(i != strs.length -1 &&!cur.children.containsKey(p)){
                return false;
            }else if(!cur.children.containsKey(p)){
                cur.children.put(p,new Trie());
            }
            cur = cur.children.get(p);
        }

        if(cur.content != -1){
            return false;
        }
        cur.content = content;

        return true;
    }

    public int get(String path) {
        Trie cur = this.root;
        String[] strs = path.split("/");
        return get(strs,1,cur);

    }

    private int get(String[] strs, int idx,Trie cur) {
        for(int i =idx;i< strs.length;i++){
            String p = strs[i];
            //!A && !B
            if(!cur.children.containsKey(p) && !p.equals("*")){
                return -1;
                //A || B
            }else if(p.equals("*")){
                for(String key : cur.children.keySet()){
                   int content = get(strs,i+1,cur.children.get(key));
                   if(content != -1){
                       return content;
                   }
                }
                return -1;
            }else {
                cur = cur.children.get(p);
            }
        }
        return cur.content;
    }

    class Trie{
        HashMap<String, Trie> children;
        int content;
        public Trie() {
            children = new HashMap<>();
            content = -1;
        }
    }
}
