package basic.knowledge.henry.algorithm.InterviewExperience.gs;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    class Interval{
        int startTime;
        int endTime;
        int price;

        public Interval(int startTime, int endTime, int price) {
            if (startTime >= endTime) {
                throw new IllegalArgumentException( "startTime greater than or equal to endTime for an interval" );
            }
            else if ( startTime < 0 || endTime < 0 || price < 0 ) {
                throw new IllegalArgumentException( "vendor information has negative values" );
            }
            this.startTime = startTime;
            this.endTime = endTime;
            this.price = price;
        }

        public Interval withMaxEnd(int max) {
            return (endTime <= max) ? this : new Interval(startTime, max, price);
        }

        public Interval withMinStart(int min) {
            return (startTime >= min) ? this : new Interval(min, endTime, price);
        }

        public  int price() {
            return this.price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return startTime == interval.startTime && endTime == interval.endTime && price == interval.price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime, price);
        }
    }


    private static class BinaryTree {
        private final Interval data;
        private BinaryTree left;
        private BinaryTree right;

        BinaryTree(Interval data) {
            this.data = data;
            left = null;
            right = null;
        }

        /**
         *  Invariant: BinaryTree is a sorted list of non-overlapping intervals.
         *
         *  This method will take the given interval and apply it to any gaps in between the intervals.
         *
         *  definition of this function:
         *  given the interval(data) as the parent node, setup its left son node and right son node
         *
         *  the val of father node(data) is definatelly bigger than the val of son node(val of the parameter in the function)
         * @param interval
         */
        void applyInterval(Interval interval) {
            //base case which shows the condition of suspending this recursive and return the result
            if(interval.startTime>= data.startTime && interval.endTime <= data.endTime){
                return;
            }


            if (interval.startTime < data.startTime) {
                Interval leftInterval = interval.withMaxEnd(data.startTime);
                if (this.left != null) {
                    this.left.applyInterval(leftInterval);
                }
                else {
                    this.left = new BinaryTree(leftInterval);
                }
            }
            if (interval.endTime > data.endTime) {
                Interval rightInterval = interval.withMinStart(data.endTime);
                if (this.right != null) {
                    this.right.applyInterval(rightInterval);
                }
                else {
                    this.right = new BinaryTree(rightInterval);
                }
            }
        }

        private void traverse(List<Interval> results) {
            if (this.left != null) {
                this.left.traverse(results);
            }
            results.add(this.data) ;
            if (this.right != null) {
                this.right.traverse(results);
            }
        }

        List<Interval> inOrder() {
            List<Interval> results = new ArrayList<>();
            this.traverse(results);
            return results;
        }
    }



    private List<Interval> getLowestPrices(List<Interval> inputIntervals) throws IllegalArgumentException {
        if (inputIntervals == null || inputIntervals.size() == 0) {
            throw new IllegalArgumentException("inputIntervals has 0 elements");
        }
        for (Interval inputInterval: inputIntervals) {
            if (inputInterval == null) {
                throw new IllegalArgumentException("inputIntervals has a NULL element");
            }
        }

        ArrayDeque<Interval> sortedIntervalQueue = inputIntervals
                .stream()
                .sorted(Comparator.comparing(Interval::price))
                .collect(Collectors.toCollection(ArrayDeque::new));

        Interval root = sortedIntervalQueue.pop();
        BinaryTree tree = new BinaryTree(root);
        for (Interval interval: sortedIntervalQueue) {
            tree.applyInterval(interval);
        }

        return tree.inOrder();
    }

    /**
     * Returns true if the tests pass. Otherwise, false.
     *
     * Additional Test Cases:
     *   Input : (1, 20 13), (7, 10, 8), (3, 8, 15), (1, 5, 20)
     *   Output: (1, 7, 13), (7, 10, 8), (10, 20, 13)
     *
     *   Input : (7, 10, 8), (3, 8, 15), (1, 5, 20), (1, 20, 4)
     *   Output: (1, 20, 4)
     *
     *   Input : (3, 6, 2), (1, 9, 3), (5, 8, 1)
     *   Output: (1, 3, 3), (3, 5, 2), (5, 8, 1), (8, 9, 3)
     */
    private boolean doTestsPass() {
        try {
            List<Interval> inputList = Arrays.asList(
                    new Interval(1, 5, 20),
                    new Interval(3, 8, 15),
                    new Interval(7, 10, 8));

            List<Interval> expectedList = Arrays.asList(
                    new Interval(1, 3, 20),
                    new Interval(3, 7, 15),
                    new Interval(7, 10, 8));

            return equalsList(expectedList,getLowestPrices(inputList));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private boolean equalsList(List<Interval> expectedList, List<Interval> lowestPrices) {
        if(expectedList.size() != lowestPrices.size()){
            return false;
        }

        int n = expectedList.size();
        for(int i =0;i< n;i++){
            if(!expectedList.get(i).equals(lowestPrices.get(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Execution entry point.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        if (solution.doTestsPass()) {
            System.out.println("All tests passed");
        }
        else {
            System.out.println("Tests failed");
        }
    }



}
