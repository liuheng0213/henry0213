package basic.knowledge.henry.algorithm.InterviewExperience.gs;

import java.util.*;


class Leetcode218 {
    public static void main(String[] args) {
        Leetcode218 obj = new Leetcode218();
        List<List<Integer>> skyline = obj.getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}});
        System.out.println(skyline);
    }


    //将建筑物的左右端点分别提取出来并排序，左端点用负高度表示表示加入操作，右端点用正高度表示移除操作
    //然后遍历所有端点，用最大堆维护当前位置的所有高度，每当最大高度发生变化时就找到了一个天际线的关键点，
    // 加入操作比maxheight更高为关键点，移除操作比maxheight低为关键点
    // 最终得到所有关键点即为天际线。
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();

        List<int[]> heights=new ArrayList<>();

        transformBuilding(buildings,heights);

        //if heights of 2 points are same then place the point with smaller height first else place point with smaller starting point

        Collections.sort(heights,(a,b)->(a[0]==b[0]) ? a[1]-b[1] : a[0]-b[0]);// TC->O(nlog n)

        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b)->(b-a));

        //seeding the Priority Queue
        pq.add(0);
        int prevMax=0;

        for(int[] height:heights){ //O(n)

            if(height[1]<0){
                pq.add(-height[1]);
            }
            else{
                pq.remove(height[1]); //O(log n)
            }

            int CurrentMax=pq.peek();
            if(CurrentMax!=prevMax)
            {
                List<Integer> subResult=new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(CurrentMax);

                res.add(subResult);
                prevMax=CurrentMax;
            }
        }

        return res;
    }
    //this will seperate the values of start point and end point with height
    //example-->[1,2,3]-->[1,-3] & [2,3]-->here -(minus) is just for convention for starting point
    private void transformBuilding(int[][] buildings,List<int[]> heights)
    {
        for(int[] building:buildings)
        {
            heights.add(new int[]{building[0],-building[2]});
            heights.add(new int[]{building[1],building[2]});
        }



    }
}

