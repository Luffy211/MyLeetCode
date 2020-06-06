package List.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayList {
    /*
    题目1：
        给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    示例:
        给定 nums = [2, 7, 11, 15], target = 9
        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]:
     */
    public static int[] twoSum(int[] nums, int target) {
        //暴力解法:O(n^2)
        /*int[] answer = new int[2];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    answer[0]=i;
                    answer[1]=j;
                }
            }
        }
        return answer;*/
        //利用哈希表O（n），containsKey的时间复杂度平均是O（1）？ 空间换时间
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /*
    题目26：
        给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
        不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

    示例1:
        给定数组 nums = [1,1,2],
        函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
        你不需要考虑数组中超出新长度后面的元素。
    示例2：
        给定 nums = [0,0,1,1,1,2,2,3,3,4],
        函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
        你不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeDuplicates(int[] nums){
        /*超出时间
        int count = 0;
        for(int i=0;i<nums.length-1;){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    count++;
                }else{
                    nums[j-count] = nums[j];
                    i = j;
                    break;
                }
            }
        }
        return nums.length-count;
        */
        int i=0;
        int count = 0;
        for(int j=i+1;j<nums.length;j++){
            if(nums[i]==nums[j]){
                count++;
            }else{
                nums[j-count] = nums[j];
                i = j;
            }
        }
        return  nums.length-count;
        /*更优雅的做法：不用count，只需要i j即可
        int i = 0;
        int j = 1;
        while(j<nums.length){
            if(nums[i]!=nums[j]){
                nums[i+1] = nums[j]
                i++;
            }
            j++;
        }
        return i+1;
         */
    }

    /*
    题目27：
        给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
        不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
        元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

    示例1:
        给定 nums = [0,1,2,2,3,0,4,2], val = 2,
        函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
        注意这五个元素可为任意顺序。
        你不需要考虑数组中超出新长度后面的元素。
 */
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        int i = 0;
        while(i<nums.length){
            if(nums[i]==val){
                count++;
            }else{
                nums[i-count] = nums[i];
            }
            i++;
        }
        return nums.length-count;
    }

    /*
题目35：搜索插入位置
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

    你可以假设数组中无重复元素。

示例1:
    输入: [1,3,5,6], 5
    输出: 2
*/
    public static int searchInsert(int[] nums, int target) {
        //二分搜索
        int start = 0;
        int end = nums.length-1;
        while(start<end){
            int mid = (start+end)/2;
            if(target==nums[mid]){
                return mid;
            }else if(target<nums[mid]){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        if(target>nums[start]){
            return start+1;
        }else {
            return start;
        }
    }

    /*
题目53：最大子序列和
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和

示例1:
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
*/
    public static int max(int a,int b,int c){
        if(a>=b){
            if(a>=c){
                return a;
            }else {
                return c;
            }
        }else{
            if(b>=c){
                return b;
            }else {
                return c;
            }
        }
    }
    public static int maxSubArray(int[] nums) {
        //动态规划法:如下出来的是最大子序列和，而不是连续子序列
        //就算是求最大子序列和，也还可以优化，用一维数组就够了？
       /* int n = nums.length;
        int[][] sum = new int[n][n];
        for(int i=0;i<n;i++){
            sum[i][i] = nums[i];
        }
        for(int c=1;c<=n-1;c++){
            System.out.println(c+"---");
            for(int i = 0;i<n-c;i++){
                System.out.println(i+": ");
                int j = i+c;
                int q = max(sum[i][j-1],sum[j][j],sum[i][j-1]+sum[j][j]);
                if(q>=sum[i+1][j]){
                    sum[i][j] = q;
                }else{
                    sum[i][j] = sum[i+1][j];
                }
                System.out.println(sum[i][j]);
            }
        }
        return sum[0][n-1];*/
       //暴力法：
 /*       int max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int localMax = nums[i];
            int sum = nums[i];
            for(int j =i+1;j<nums.length;j++){
                sum = sum+nums[j];
                if(sum>localMax){
                    localMax = sum;
                }
            }
            if(localMax>max){
                max = localMax;
            }
        }*/
        //动态规划2：dp[i]表示以nums[i]结尾的最大连续子序列和
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);  //必定包含nums[i] 所以保证连续性
            if(dp[i]>max){
                max = dp[i];
            }
        }
        return max;

        //优雅写法:和上面的其实核心思想一致,但是和贪心也有点像
        /*int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {       //sum大于0，说明前面一连串贡献为正，要保留，加上当前的num
                sum += num;
            } else {            //sum小于0即前面一连串贡献为负，丢弃掉，从当前num开始继续滚动累加
                sum = num;
            }
            ans = Math.max(ans, sum);       //ans保存全局最大
        }
        return ans;*/
    }


    public static void main(String[] args) {
        /*
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] answer = twoSum(nums,target);
        System.out.println(Arrays.toString(answer));
        */

        int [] nums = {-3,4,-1,2,1,-5};
        System.out.println(maxSubArray(nums));
        //System.out.println(Arrays.toString(nums));

    }

}
