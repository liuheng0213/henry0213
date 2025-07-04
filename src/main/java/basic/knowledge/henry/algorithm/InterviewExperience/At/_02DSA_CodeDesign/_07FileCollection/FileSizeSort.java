package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._07FileCollection;

import java.util.*;


/**
 * follow up  How would you handle this in a multithreaded env
 * o（nlogk）
 */
public class FileSizeSort {

    PriorityQueue<CollectionTag> queue; //PriorityBlockingQueue
    Map<String, List<File>> map;
    Map<String, CollectionTag> nameToCollectionTag;
    int totalSize;
    int n = 4;
    public FileSizeSort() {
        this.totalSize = 0;
        this.queue = new PriorityQueue<>((a,b)->a.totalSize - b.totalSize);
        this.map = new HashMap<>();
        this.nameToCollectionTag = new HashMap<>();

    }

    public void addFile(File file){
        String tag = file.tag;
        int size = file.size;

        nameToCollectionTag.putIfAbsent(tag,new CollectionTag());
        nameToCollectionTag.get(tag).addSize(size);

        CollectionTag current = nameToCollectionTag.get(tag);

        //update queue
        if(this.queue.size() < this.n){
            queue.add(current);
        }else{
            if(queue.peek().totalSize < current.totalSize){
                queue.poll();
                queue.add(current);
            }

        }
    }

    public List<CollectionTag> topN(){
        List<CollectionTag> topN = new ArrayList<>();
        while(!queue.isEmpty()){
            topN.add(queue.poll());
        }

        return topN;
    }


    public void addFile2(File file){
        String tag = file.tag;
        List<File> files = map.getOrDefault(tag, new ArrayList<>());
        files.add(file);
        map.put(tag,files);
        totalSize+= file.size;
    }




    public List<CollectionTag> topN2(int n) {
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


        while(!queue.isEmpty()){
            topN.add(queue.poll());
        }
//        topN.addAll(queue);

        return topN;
    }
}
