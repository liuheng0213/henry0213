package basic.knowledge.henry.ThreadRelevant._26threadSafe.countThreadSafe;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashmapTest {
    @Test
    public void testMap1() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.put("b","v"); // 输出 B
        System.out.println(v);
        String v1 = map.put("c","v");
        System.out.println(v1); // 输出：NULL
    }


    @Test
    public void testMap2() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String val = map.compute("b", (k, v) -> "v"); // 输出 v
        System.out.println(val);
        String v1 = map.compute("c", (k, v) -> "v"); // 输出 v
        System.out.println(v1);
    }

    @Test
    public void testMap3() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.putIfAbsent("b","v");  // 输出 B
        System.out.println(v);
        String v1 = map.putIfAbsent("c","v");  // 输出 null
        System.out.println(v1);
    }

    @Test
    public void testMap4() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.computeIfAbsent("b",k->"v");  // 输出 B
        System.out.println(v);
        String v1 = map.computeIfAbsent("c",k->"v"); // 输出 v
        System.out.println(v1);
    }







}
