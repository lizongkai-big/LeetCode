package Ordered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _56__Merge_Intervals {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     * 不算优点的优点：在原有的ArrayList中做处理
     * 缺点：List.remove(int index); 涉及大量的数组复制操作，效率很低；不如直接新建一个List
     * @param intervals
     * @return
     */
    public List<Interval> merge_mine(List<Interval> intervals) {
        bubbleSort(intervals);
        int inx = 0;
        for(int i = 1; i < intervals.size();) {
            if(intervals.get(inx).start == intervals.get(i).start ||
                    intervals.get(inx).start < intervals.get(i).start && intervals.get(inx).end >= intervals.get(i).start ||
                    intervals.get(inx).start > intervals.get(i).start && intervals.get(i).end >= intervals.get(inx).start
                    ) {
                intervals.get(inx).start = Math.min(intervals.get(inx).start, intervals.get(i).start);
                intervals.get(inx).end = Math.max(intervals.get(inx).end, intervals.get(i).end);
                intervals.remove(i);
            }
            else {
                i++;
                inx ++;
            }
        }
        return intervals;
    }



    public List<Interval> merge_optimization1(List<Interval> intervals) {
        List<Interval> list=new ArrayList<>();

        int []start=new int[intervals.size()];
        int []end=new int[intervals.size()];

        for(int i=0;i<intervals.size();i++){
            start[i]=intervals.get(i).start;
            end[i]=intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        for(int i=0,j=0;i<intervals.size();i++){
            /* 到了最后一个元素或者不再有重叠*/
            if(i==intervals.size()-1 || start[i+1]>end[i]){
                list.add(new Interval(start[j],end[i]));
                j=i+1;
            }
        }

        return list;

    }

    public List<Interval> merge_optimization2(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        /*线性单链表 好评！*/
        List<Interval> result = new LinkedList<Interval>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        Interval last = new Interval(0,0);
        for (Interval interval : intervals) {
            /*if (interval.start > end){
                last = new Interval(start, end);
                result.add(last);
            }
            else {
                last.end = Math.max(last.end, interval.end);
            }*/
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }

    public void bubbleSort(List<Interval> intervals) {
        boolean swap = false;
        for(int i = 0; i < intervals.size(); i++) {
            for(int j = 0; j <= intervals.size()-i-1-1; j++) {
                if(intervals.get(j).start > intervals.get(j+1).start) {
                    Interval temp = intervals.get(j);
                    intervals.set(j, intervals.get(j+1));
                    intervals.set(j+1, temp);
                    swap = true;
                }
            }
            // if(!swap) break;
        }
    }
}
