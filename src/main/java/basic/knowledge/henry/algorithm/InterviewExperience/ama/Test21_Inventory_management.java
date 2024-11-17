package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test21_Inventory_management {

        public int findPartition(List<Integer> inv2, int sumInv1, int sumInv2, int n) {
            if (sumInv1 == sumInv2) return n;
            if (sumInv1 > sumInv2) return n - 1;

            int left = 0, right = n - 1;
            int currentSum = 0;
            Random rand = new Random();

            while (left < right) {
                int pivotIndex = left + rand.nextInt(right - left + 1);
                int pivotValue = inv2.get(pivotIndex);
                int l = left - 1, r = right + 1;

                while (true) {
                    do { l++; } while (inv2.get(l) < pivotValue);
                    do { r--; } while (inv2.get(r) > pivotValue);
                    if (l >= r) break;
                    Collections.swap(inv2, l, r);
                }

                int leftPartitionSum = inv2.subList(left, r + 1).stream().mapToInt(Integer::intValue).sum();

                if (currentSum + leftPartitionSum < sumInv1) {
                    currentSum += leftPartitionSum;
                    left = r + 1;
                } else if (currentSum + leftPartitionSum > sumInv1) {
                    right = r;
                } else {
                    return r + 1;
                }
            }

            if (currentSum + inv2.get(left) <= sumInv1) left++;
            return left;
        }
}
