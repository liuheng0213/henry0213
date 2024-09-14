package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._08arrAndMatrix;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//在数组中找到出现次数大于N/K的数  贪心
public class _03TimesMoreThanN_K {
    public static void main(String[] args) {
        _03TimesMoreThanN_K timesMoreThanN_k = new _03TimesMoreThanN_K();
        int[] arr1 = new int[]{2, 2, 4, 3, 1, 5};
        timesMoreThanN_k.getIfKequals2(arr1);
      /*  int[] arr = new int[]{1, 3, 5, 3, 4, 3, 3, 8, 3, 3};
        timesMoreThanN_k.getN_K(arr, 3);*/
    }

    private void getN_K(int[] arr, int k) {
        HashMap<Integer, Integer> candMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (candMap.containsKey(arr[i])) {
                candMap.put(arr[i], candMap.get(arr[i]) + 1);
            } else {
                if (candMap.size() == k - 1) {
                    allMinusOneInCandMap(candMap);
                } else {
                    candMap.put(arr[i], 1);
                }
            }
        }

        Map<Integer, Integer> realMap = getReal(arr, candMap);
        System.out.println("===");
    }

    /**
     * 一定要有这个  candMap 只是抵消后的有优势的key value 其中key 是真实的 但是次数value必须重新遍历拿到真实的times
     *
     * @param arr
     * @param candMap
     * @return
     */
    private Map<Integer, Integer> getReal(int[] arr, HashMap<Integer, Integer> candMap) {
        Map<Integer, Integer> realMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (candMap.containsKey(arr[i])) {
                if (realMap.containsKey(arr[i])) {
                    realMap.put(arr[i], realMap.get(arr[i]) + 1);
                } else {
                    realMap.put(arr[i], 1);
                }
            }

        }
        return realMap;
    }

    private void allMinusOneInCandMap(HashMap<Integer, Integer> candMap) {
        //List<Integer> removeList = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entries = candMap.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> cur = iterator.next();
            Integer key = cur.getKey();
            Integer value = cur.getValue();
            if (value > 1) {
                candMap.put(key, value - 1);
                if (value == 1) {
                    iterator.remove();
                }
            }
        }
     /*   for(Integer removeKey : removeList){
            candMap.remove(removeKey);
        }*/
    }

    /**
     * 只可计算大于1/2 的等于1/2的计算不了  candi 不准
     *
     * @param arr
     */
    private void getIfKequals2(int[] arr) {
        //记录占优势的数的个数,所谓占优势就是当前最有可能次数大于一半的数, K = 2时
        // 这个数只有可能是K - 1 = 1个,所以一个变量times记录足矣
        int times = 0;
        int dominativeNum = 0;//或者说是遍历后出现次数最多的数  candidateNum


        for (int i = 0; i < arr.length; i++) {
            //优势耗尽,重新指定优势数
            if (times == 0) {
                dominativeNum = arr[i];
                times = 1;
            } else if (arr[i] == dominativeNum) {
                times++;
            } else {
                times--;
            }
        }
        //到这里时 上面得times并不是真正得dominativeNum出现得真是次数,截止目前最重要得是 dominativeNum得选择
        times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == dominativeNum) {
                times++;
            }
        }


        if (times > arr.length / 2) {
            System.out.println("this num is " + dominativeNum);
        } else {
            System.out.println("No such num");
        }
    }
}
