package basic.knowledge.henry.algorithm.InterviewExperience.rk;

import java.util.HashMap;

public class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        int r1 = lruCache.get(1);
        System.out.println(r1);
        lruCache.put(3,3);
        int r2 = lruCache.get(2);
        System.out.println(r2);

    }
    int capacity;
    HashMap<Integer, DoubleDirNode> keyNode;

    DoubleDirLinkedList list;
    public LRUCache(int capacity){
        this.capacity = capacity;
        this.keyNode = new HashMap<>();
        this.list = new DoubleDirLinkedList();
    }

    public int get(int key){
        if(keyNode.containsKey(key)){
            DoubleDirNode doubleDirNode = keyNode.get(key);
            list.move2Tail(doubleDirNode);
            return doubleDirNode.val;
        }

        return -1;
    }



    public void put(int key,int val){
        if(keyNode.containsKey(key)){
            DoubleDirNode doubleDirNode = keyNode.get(key);
            doubleDirNode.val = val;
            list.move2Tail(doubleDirNode);
            keyNode.put(key,doubleDirNode);
        }else{
            if(keyNode.size() == this.capacity){
                int deleted = list.removeHead();
                keyNode.remove(deleted);
            }
            DoubleDirNode node = new DoubleDirNode(key,val);
            keyNode.put(key,node);
            list.add(node);

        }
    }

    class DoubleDirLinkedList{
        DoubleDirNode head;
        DoubleDirNode tail;

        public DoubleDirLinkedList() {
            this.head = new DoubleDirNode(-1,-1);
            this.tail = new DoubleDirNode(-1,-1);
            this.tail.pre = this.head;
            this.head.next = this.tail;
        }

        public void move2Tail(DoubleDirNode node) {
           removeNode(node);
           add(node);
        }

        private void removeNode(DoubleDirNode node) {
            DoubleDirNode pre = node.pre;
            DoubleDirNode next = node.next;
            pre.next = next;
            next.pre = pre;
            node.next = null;
            node.pre  = null;
        }

        public void add(DoubleDirNode node) {
            this.tail.pre.next = node;
            node.pre = this.tail.pre;
            node.next = this.tail;
            this.tail.pre = node;

        }

        public int removeHead() {
            int deleted = this.head.next.key;
            removeNode(this.head.next);
            return deleted;
        }
    }


    class DoubleDirNode {
        int key;
        int val;
        DoubleDirNode pre;
        DoubleDirNode next;

        public DoubleDirNode(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
}
