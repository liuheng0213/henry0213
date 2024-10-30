package basic.knowledge.henry;

import org.junit.Test;

import java.util.*;

public class BasicTest {


    @Test
    public void testTreeSet() {
        List<Integer> list = new ArrayList<>();

        String str = "abc123";
        String substring = str.substring(3,5);

        TreeSet<Integer> set = new TreeSet<>();
        set.add(2);
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        System.out.println();
        if (set != null) {
            Iterator<Integer> iterator = set.iterator();
            while(iterator.hasNext()){
                Integer next = iterator.next();
                if(set.higher(next) != null){
                    iterator.remove();
                }
            }

            System.out.println();
        }

    }

    @Test
    public void testMapC() {
        //没有update,
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        //存在原key 返回原value 值B
        String v = map.computeIfAbsent("b", k -> "v");  // 输出 B
        System.out.println(v);
        //不存在原key 才加入新的值v,
        String v1 = map.computeIfAbsent("c", k -> "v"); // 输出 v
        System.out.println(v1);
    }

    @Test
    public void testMap() {
        //没有update
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        //存在原key 返回原value 值B, 不更新
        String v = map.putIfAbsent("b", "v");  // 输出 B
        System.out.println(v);
        //不存在原key 才加入新的值v， 但是返回Null
        String v1 = map.putIfAbsent("c", "v");  // 输出 null
        System.out.println(v1);
    }

    class User {
        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
