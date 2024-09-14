package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch3._01Symbol_table_arr_linkList;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.entity.User;
import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch1.queue.MyQueue;

/**
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST_Arr<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        BinarySearchST_Arr<User, String> st = new BinarySearchST_Arr<>(10);
        st.put(new User(1), "1");
    }

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST_Arr(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public int size(Key lo,Key hi){
        int i = rank(lo);
        int j = rank(hi);

        if(contains(hi)){
            return j - i + 1;
        }

        return j - i;
    }

    public int size(Key key){
        return rank(key) + 1;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        return rank(key, lo, hi);
    }

    private int rank(Key key, int lo, int hi) {
        if (lo > hi) {
            return lo;
        }

        int mid = (lo + hi) / 2;
        int cmp = keys[mid].compareTo(key);
        if (cmp > 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp < 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        
        if(this.N == this.keys.length){
            resize(2 * this.keys.length);
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
        assert check();
    }

    private void resize(int length) {
        BinarySearchST_Arr<Key, Value> st = new BinarySearchST_Arr<>(length);
        for (int i = 0; i < this.N; i++) {
            if (keys[i] != null) {
                st.put(keys[i], vals[i]);
            }
        }

        this.keys = st.keys;
        this.vals = st.vals;
        this.N = st.N;
    }

    /**
     * Exercise 3.1.16
     *
     * @param key
     */
    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            for (int j = i; j < N - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            N--;
            keys[N] = null;
            vals[N] = null;
        }
        if(this.N == this.keys.length / 4){
            resize(this.keys.length / 2);
        }
        assert check();
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    /**
     * Exercise 3.1.17
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N) {
            if (keys[i].compareTo(key) == 0) {
                return key;
            } else if (i > 0) {
                return keys[i - 1];
            }
        }
        return null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        MyQueue<Key> q = new MyQueue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        return get(key) != null;
    }

    // Add for Exercise 3.1.29
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    // Exercise 3.1.30
    private boolean check() {
        return isSorted() && rankCheck();
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }
}
