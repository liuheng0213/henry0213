package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;

public class FileSizeStatistics_followUP {
    public static void main(String[] args) {
        FileSizeStatistics_followUP fileSizeStatistics = new FileSizeStatistics_followUP();
        fileSizeStatistics.insert(new File("a", 100), "-1");
        fileSizeStatistics.insert(new File("w", 200), "1");
        fileSizeStatistics.insert(new File("w", 200), "2");
        fileSizeStatistics.insert(new File("w", 200), "3");
        fileSizeStatistics.insert(new File("c", 200), "1");
        fileSizeStatistics.insert(new File("d", 300), "2");
        fileSizeStatistics.insert(new File("f", 500), "1");
        fileSizeStatistics.insert(new File("g", 400), "3");
        fileSizeStatistics.insert(new File("e", 100), "-1");

        fileSizeStatistics.getTotalSize();

        fileSizeStatistics.topKsize(3);

    }

    HashMap<String, Integer> collectionMap = new HashMap<>();
    List<File> fileList = new ArrayList<>();

    static class File {
        String name;
        int size;

        public List<String> getCollectionIds() {
            return collectionIds;
        }

        public void addCollections(String cid){
            this.collectionIds.add(cid);
        }

        List<String> collectionIds;

        public File(String name, int size) {
            this.name = name;
            this.size = size;
            this.collectionIds = new ArrayList<>();
        }

    }

    public void insert(File file, String cid) {
        file.addCollections(cid);
        this.fileList.add(file);
    }


    public void getTotalSize() {
        int totalSize = 0;
        for (File f : fileList) {
            totalSize += f.size;
        }
        System.out.println("The total size of files processed: " + totalSize);
    }

    public void topKsize(int k) {
        //Top 2 collections: collection1 : 400 collection2 : 300
        for (File f : fileList) {
            for (String cid : f.getCollectionIds()){
                this.collectionMap.put(cid, this.collectionMap.getOrDefault(cid, 0) + f.size);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(this.collectionMap.entrySet());
        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());
        System.out.println("Top " + k + " collections: ");
        for (int i = 0; i < k; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.print("collection" + entry.getKey() + ": " + entry.getValue() + " ");
        }
    }
}
