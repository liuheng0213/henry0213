package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._07FileCollection;

import java.util.*;


/**
 * follow up  How would you handle this in a multithreaded env
 * o（nlogk）
 */
public class FileSizeSort {

    PriorityQueue<CollectionTag> queue; //PriorityBlockingQueue
    Map<String, List<File>> map;
    int totalSize;

    public FileSizeSort() {
        this.totalSize = 0;
        this.queue = new PriorityQueue<>((a,b)->a.totalSize - b.totalSize);
        this.map = new HashMap<>();
    }

    public void addFile(File file){
        String tag = file.tag;
        List<File> files = map.getOrDefault(tag, new ArrayList<>());
        files.add(file);
        map.put(tag,files);
        totalSize+= file.size;
    }

    public List<CollectionTag> topN(int n) {
        n = Math.min(map.size(),n);

        for(String collectionName : map.keySet()){
            List<File> files = map.get(collectionName);
            int filesSize = 0;
            for(File f : files){
                filesSize+=f.size;
            }
            CollectionTag tag = new CollectionTag(collectionName,filesSize);

            if(queue.size()< n){
                queue.add(tag);
            }else if(queue.size() == n){
                if(queue.peek().totalSize < tag.totalSize){
                    queue.poll();
                    queue.add(tag);
                }
            }
        }
        List<CollectionTag> topN = new ArrayList<>();
        topN.addAll(queue);

        return topN;
    }
}
