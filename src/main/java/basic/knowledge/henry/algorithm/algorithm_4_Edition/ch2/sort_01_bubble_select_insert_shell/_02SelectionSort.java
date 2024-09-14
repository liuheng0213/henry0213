package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch2.sort_01_bubble_select_insert_shell;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.util.SortUtil;

/**
 * 逆序最坏情况
 */
public class _02SelectionSort {
    public static void main(String[] args) {
//        sort(MockData.DOUBLE_FOR_SORT_MOCK);
//        SortUtil.isSorted(MockData.DOUBLE_FOR_SORT_MOCK);
        Integer[] as = new Integer[]{5, 4, 3, 2, 1};
        sort(as);
        SortUtil.isSorted(as);
    }
    //每一轮把最小的放前
    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (SortUtil.less(arr[j], arr[min])) {
                    SortUtil.exch(arr, j, min);
                }
            }
        }
    }
}
