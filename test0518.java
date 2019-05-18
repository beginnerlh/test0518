/*给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。*/
package lianxi0518;

import java.util.Arrays;

public class test0518 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] a = s.merge(intervals);
        for(int i = 0;i<a.length;i++){
            System.out.print(Arrays.toString(a[i]));
        }
    }

}

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length < 2){
            return intervals;
        }
        boolean[] flag = new boolean[intervals.length];
        boolean f;
        int count = intervals.length;
        for(int i=0; i<intervals.length; i++){
            f = false;
            if(!flag[i]){
                for(int j=i+1; j<intervals.length; j++){
                    int num1 = Math.min(intervals[i][0],intervals[j][0]);//存较小的
                    int num2,num3;
                    if(num1 == intervals[i][0]){
                        num2 = intervals[i][1];
                        num3 = intervals[j][0];
                    }else{
                        num2 = intervals[j][1];
                        num3 = intervals[i][0];
                    }
                    if(!flag[j] && num3 >= num1 && num3 <= num2){//可以合并
                        intervals[i][0] = Math.min(intervals[i][0],intervals[j][0]);
                        intervals[i][1] = Math.max(intervals[i][1],intervals[j][1]);
                        flag[j] = true;
                        count--;
                        f = true;
                    }
                }
                if(f){
                    i--;
                }
            }
        }
        int[][] result = new int[count][2];
        int index = 0;
        for(int k=0; k<intervals.length; k++){
            if(!flag[k]){
                result[index][0] = intervals[k][0];
                result[index][1] = intervals[k][1];
                index++;
            }
        }
        return result;
    }
}