package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._05RouteService;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询时间复杂度 o(len) len 为所有path node 的长度和
 */
public class RouterService {
    PathNode root = new PathNode();
    public boolean adddrequest(String path, int content) {
        String[] folders = path.split("/");
        PathNode cur = root;
        for(int i =1;i< folders.length;i++){
            if(!cur.children.containsKey(folders[i])){
                if (i != folders.length - 1) return false;
                cur.children.put(folders[i],new PathNode());
            }
            cur = cur.children.get(folders[i]);
        }

        if(cur.content != -1){
            return false;
        }
        cur.content = content;
        return true;
    }

    public int callrequest(String path) {
        String[] folders = path.split("/");
        PathNode cur = root;
        return findWithWildCard(1,cur,folders);

    }

    private int findWithWildCard(int idx, PathNode cur, String[] folders) {
        for(int i = idx;i< folders.length;i++){
            String folder = folders[i];
            if(!folder.equals("*")){
                if(!cur.children.containsKey(folder)){
                    return -1;
                }
                cur = cur.children.get(folder);
            }else{
                for(String key : cur.children.keySet()){
                    int content = findWithWildCard(i+1,cur.children.get(key),folders);
                    if(content != -1){
                        return content;
                    }
                }
                return -1;
            }
        }
        if(cur.content != -1){
            return cur.content;
        }

        return -1;

    }

    class PathNode{
        private Map<String,PathNode> children;
        private int content;



        public PathNode() {
            this.children = new HashMap<>();
        }
    }
}
