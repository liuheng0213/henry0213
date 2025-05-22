package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTaggingSystem {
    public static void main(String[] args) {
        FileTaggingSystem solution = new FileTaggingSystem();
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
        solution.filesInCollection(files);
        System.out.println(" another solution=========");

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
    private void filesInCollection(List<File> files) {
        HashMap<String,List<File>> collection2FileList = new HashMap<>();
        for(File file : files){
            for(CollectionNode collectionNode  : file.collections){
                populateCollection2FileList(collection2FileList,file,collectionNode);
            }

        }

        for(Map.Entry<String,List<File>> entry : collection2FileList.entrySet()){
            System.out.print(entry.getKey() + " : " + printList(entry.getValue()) + "   ");
            System.out.println(";");
        }

    }

    /**
     * attach collectionNode to file
     * @param collection2FileList
     * @param file
     * @param collectionNode
     */
    private void populateCollection2FileList(HashMap<String, List<File>> collection2FileList, File file, CollectionNode collectionNode) {
        String cName =collectionNode.name;
        List<File> fileList = collection2FileList.getOrDefault(cName, new ArrayList<>());
        fileList.add(file);
        collection2FileList.put(cName,fileList);

        if(collectionNode.subCollections.isEmpty()){
            return;
        }
        for(CollectionNode cNode :collectionNode.subCollections){
            populateCollection2FileList(collection2FileList,file,cNode);
        }
    }

    static class CollectionNode {
        String name;
        List<CollectionNode> subCollections;

        public CollectionNode(String name) {
            this.name = name;
            this.subCollections = new ArrayList<>();
        }
    }

    static class File {
        String name;
        int size;
        List<CollectionNode> collections;

        public File(String name, int size) {
            this.name = name;
            this.size = size;
            this.collections = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }
    }
}
