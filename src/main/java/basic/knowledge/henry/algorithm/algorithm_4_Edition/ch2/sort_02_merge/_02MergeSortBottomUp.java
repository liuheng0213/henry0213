package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch2.sort_02_merge;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.mock.MockData;
import basic.knowledge.henry.algorithm.algorithm_4_Edition.util.SortUtil;

public class _02MergeSortBottomUp {
    private static Double[] aux;

    public static void main(String[] args) {
        sort(MockData.DOUBLE_FOR_SORT_MOCK);
        SortUtil.isSorted(MockData.DOUBLE_FOR_SORT_MOCK);
    }

    public static void sort(Double[] a) {
        int n = a.length;
        aux = new Double[a.length];
        for (int size = 1; size < n; size *= 2) {
            for (int index = 0; index < n - size; index = 2 * size + index) {//index <n-size 一定要考虑最后有可能出现size merge 1的情况
                merge(a, index, size + index - 1, Math.min(2 * size + index - 1, n - 1));
            }
        }
    }


    private static void merge(Double[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (SortUtil.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

    }
}
