package basic.knowledge.henry.algorithm.weakHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class TestWeakHashMAp {

    public static void main(String[] args) {
//        testWeakHashMap();
        testHashMap();
    }

    public static void testWeakHashMap() {

        Map<Object, String> weakMap = new WeakHashMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        weakMap.put(key1, "I am strongly referenced");
        weakMap.put(key2, "I will be GC'd soon");

        System.out.println("Before GC: " + weakMap);

        // Remove strong reference for key2
        key2 = null;
//        key1 = null;

        // Hint the GC to run
        System.gc();

        // Give the GC some time
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("After GC: " + weakMap);
    }

    public static void testHashMap() {
        Map<Object, String> map = new HashMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        map.put(key1, "I am strongly referenced");
        map.put(key2, "I am strongly referenced");

        System.out.println("Before GC: " + map);

        // Remove strong reference for key2
        key2 = null;
        key1 = null;

        // Hint the GC to run
        System.gc();

        // Give the GC some time
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("After GC: " + map);
    }

}

