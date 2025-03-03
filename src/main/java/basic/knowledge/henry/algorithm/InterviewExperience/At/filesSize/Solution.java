package basic.knowledge.henry.algorithm.InterviewExperience.At.filesSize;

import java.util.*;

/**
 * 计算每个collelction有哪些文件以及 每个collection的的size
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<File> files = new ArrayList<>();
        File a = new File("a", 100);
        File b = new File("b", 200);
        File c = new File("c", 300);
        File d = new File("d", 400);
        CollectionNode c1 = new CollectionNode("c1");
        CollectionNode c2 = new CollectionNode("c2");
        CollectionNode c3 = new CollectionNode("c3");
        CollectionNode c4 = new CollectionNode("c4");
        a.collections.add(c1);
        a.collections.add(c2);
        b.collections.add(c3);
        b.collections.add(c4);
        c.collections.add(c4);
        d.collections.add(c1);

        c1.subCollections.add(c2);
        c1.subCollections.add(c4);
        c2.subCollections.add(c3);

        files.add(a);
        files.add(b);
        files.add(c);
        files.add(d);
        solution.FilesInCollection(files);
        System.out.println(" another solution=========");
        solution.FilesInCollection2(files);
        System.out.println("end");
    }
    private void FilesInCollection2(List<File> files) {
        HashMap<String,List<File>> collectionToSize = new HashMap<>();
        for(File f : files){
            populate(collectionToSize,f.collections,f);
        }


        for(Map.Entry<String,List<File>> entry : collectionToSize.entrySet()){
            System.out.print(entry.getKey() + " : " + printList(entry.getValue()) + "   ");
            System.out.println(";");
        }

    }

    /**
     * populate collectionToSize by collections
     * @param collectionToSize
     * @param collections
     * @param f
     */
    private void populate(HashMap<String, List<File>> collectionToSize, List<CollectionNode> collections, File f) {
        if(collections == null || collections.isEmpty()){
            return;
        }

        for(CollectionNode c : collections){
            collectionToSize.putIfAbsent(c.name,new ArrayList<>());
            collectionToSize.get(c.name).add(f);

            populate(collectionToSize,c.subCollections,f);
        }
    }



    private void FilesInCollection(List<File> files) {
        HashMap<String,List<File>> collectionToSize = new HashMap<>();
        for(File f : files){
            List<CollectionNode> collectionNodes = f.collections;
            for(CollectionNode c : collectionNodes){
                populate(collectionToSize,c,f);
            }
        }


        for(Map.Entry<String,List<File>> entry : collectionToSize.entrySet()){
            System.out.print(entry.getKey() + " : " + printList(entry.getValue()) + "   ");
            System.out.println(";");
        }

    }


    /**
     * definition:  populate collectionToSize by CollectionNode
     * @param collectionToSize
     * @param c
     * @param f
     */
    private void populate(HashMap<String, List<File>> collectionToSize, CollectionNode c,File f) {
        collectionToSize.putIfAbsent(c.name,new ArrayList<>());
        collectionToSize.get(c.name).add(f);

        for(CollectionNode sub_c : c.subCollections){
            populate(collectionToSize,sub_c,f);
        }
    }

    private String printList(List<File> value) {
        String str = "";
        int size = 0;
        for(File f : value){
            size+= f.size;
            str += "name is " + f.name + " ";
        }
        str += ". total size is " +size;
        return str;
    }
}

class File {
    String name;
    int size;
    List<CollectionNode> collections;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.collections = new ArrayList<>();
    }

}


class CollectionNode {
    String name;
    List<CollectionNode> subCollections;

    public CollectionNode(String name) {
        this.name = name;
        this.subCollections = new ArrayList<>();
    }
}