package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch1.queue;


import basic.knowledge.henry.algorithm.algorithm_4_Edition.exception.ListIsEmptyException;

import java.util.Iterator;

/**
 * 练习1.3.38
 */
public class GeneralizedQueueByLink1_3_38<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;


    private class Node {
        Item t;
        Node next;
        Node previous;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public int size() {
        return N;
    }

    //放到last后  dequeue从first出
    public void enqueue(Item t) {
        if (!isEmpty()) {
            Node oldLast = last;
            Node current = new Node();
            current.t = t;
            current.next = null;
            current.previous = oldLast;
            last = current;
            oldLast.next = last;
        } else {
            Node current = new Node();
            current.t = t;
            current.next = null;
            current.previous = null;
            last = current;
            first = current;
        }
        N++;
    }

    public Item dequeue() {
        if(isEmpty()){
            throw new ListIsEmptyException("size =0");
        }
        if(size() == 1){
            Item t = first.t;
            first = null;
            last = null;
            N--;
            return  t;
        }else {
            Node oldFirst = first;
            this.first = oldFirst.next;
            this.first.previous = null;
            N--;
            return oldFirst.t;
        }
    }

    /**
     * 练习1.3.38
     *
     * @param k
     * @return
     */
    public Item delete(Item k) {
        for(Node current = this.first;current != null;current = current.next){
            if(current.t == k){
                //size == 1
                if(current == this.first && current == this.last){
                    this.first =null;
                    this.last =null;
                }
                //删除first
                else if(current == this.first){
                    first = first.next;
                    first.previous = null;
                }
                //删除last
                else if(current == this.last){
                    this.last = current.previous;
                    this.last.next = null;
                }
                else{
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                this.N--;
                return current.t;
            }
        }
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new GeneralizedQueueByLinkIterator();
    }

    private class GeneralizedQueueByLinkIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item t = current.t;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {
            //do nothing
        }
    }
}
