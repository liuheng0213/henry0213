package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch2.sort_02_merge;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.mock.MockData;
import basic.knowledge.henry.algorithm.algorithm_4_Edition.util.SortUtil;

//优化
public class _05E2_2_10 {

    public static void main(String[] args) {
        sort(MockData.DOUBLE_FOR_SORT_MOCK);
        SortUtil.isSorted(MockData.DOUBLE_FOR_SORT_MOCK);
    }

    public static void sort(Double[] a) {
        sort(a, new Double[a.length], 0, a.length - 1);
    }

    public static void sort(Double[] a, Double[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (SortUtil.less(a[mid + 1], a[mid])) {
            merge(a, aux, lo, mid, hi);
        }
    }

    private static void merge(Double[] a, Double[] aux, int lo, int mid, int hi) {

        int auxIndex = lo;

        for (int k = lo; k <= mid && auxIndex <= mid; k++) {
            aux[auxIndex++] = a[k];
        }

        for (int k = hi; k >= mid + 1 && auxIndex <= hi; k--) {
            aux[auxIndex++] = a[k];
        }



        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j--];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (SortUtil.less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
        }


    }
}
