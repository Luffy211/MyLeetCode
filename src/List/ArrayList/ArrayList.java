package List.ArrayList;

import java.util.*;
//import java.util.ArrayList;


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

        /*
题目66：加一
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。

示例1:
输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。*/

    public static int[] plusOne(int[] digits) {
        //这样解会超出int/long的范围，不行！！！
        /*
        int n = digits.length;
        int sum = 0;
        for(int i=0;i<n;i++){
            sum = digits[i] + sum*10;
        }
        System.out.println(Integer.MAX_VALUE);
        System.out.println(sum);
        int newSum = sum+1;
        System.out.println(newSum);
        if(newSum/new Double(Math.pow(10,n)).intValue()==0){
            int[] ans = new int[n];
            for(int i=n-1;i>=0;i--){
                int tmp = newSum%10;
                ans[i] = tmp;
                newSum /= 10;
            }
            return ans;
        }else{
            int[] ans = new int[n+1];
            for(int i=n;i>=0;i--){
                int tmp = newSum%10;
                ans[i] = tmp;
                newSum /= 10;
            }
            return ans;
        }
        */
        int n = digits.length;
        int sign = 0;
        if(digits[n-1]+1==10){
            sign = 1;
            digits[n-1] = 0;
        }else{
            digits[n-1] += 1;
            return digits;
        }
        for(int i=n-2;i>=0;i--){
            if(digits[i]+sign!=10){
                digits[i] += sign;
                return digits;
            }else{
                digits[i] = 0;
                sign=1;
            }
        }
        int [] ans = new int[n+1];
        Arrays.fill(ans,0);
        ans[0]=1;
        return ans;
    }

    /*
题目88：合并两个有序数组
给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
说明:
初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素

示例1:
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
输出: [1,2,2,3,5,6]
*/
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //双指针
        //假设长度是m+n 不占用额外空间：可申请空间的话很容易
        /*for(int i=0;i<n;i++){
            nums1[m+n-1-i]=nums2[i];
        }
        System.out.println(Arrays.toString(nums1));
        int i=0;
        int j=m+n-1;
        while(j>=m) {
            while (i < j) {
                if (nums1[i] <= nums1[j]) {
                    i++;
                } else {
                    int tmp = nums1[i];
                    nums1[i] = nums1[j];
                    nums1[j] = tmp;
                    i++;
                }
            }
            j--;
            i=0;
        }
        */
        //nums1空间集中在后面，所以可以从nums1和nums2后面往前选出大的填充到后面往前
        //思维惯性：从小到大就从小端开始比！！！
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while(len1 >= 0 && len2 >= 0) {
            // 注意--符号在后面，表示先进行计算再减1，这种缩写缩短了代码
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);

    }

    /*
题目118：杨辉三角
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
在杨辉三角中，每个数是它左上方和右上方的数的和。

示例1:
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new java.util.ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        // 如果没有返回则直接设置第一行
        res.add(new java.util.ArrayList<Integer>());
        res.get(0).add(1);
        // 从第二行开始遍历
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new java.util.ArrayList<>();
            row.add(1);
            // 内层循环，下标除去0和最后i，首尾位置默认为1
            for (int j = 1; j < i; j++) {
                row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
/*
题目119:杨辉三角2
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

示例:
输入: 3
输出: [1,3,3,1]
 */
//大神总结:一种就是用pre变量将要被覆盖的变量存起来，另一种就是倒着进行。另外求组合数的时候，要防止int的溢出。
    public static List<Integer> getRow(int rowIndex) {
        //暴力无空间优化:
        /*
        List<List<Integer>> t = new java.util.ArrayList<>();
        t.add(new java.util.ArrayList<Integer>());
        t.get(0).add(1);
        for(int i=1;i<=rowIndex;i++){
            List<Integer> tmp = new java.util.ArrayList<Integer>();
            tmp.add(1);
            for(int j=1;j<i;j++){
                tmp.add(t.get(i-1).get(j-1)+t.get(i-1).get(j));
            }
            tmp.add(1);
            t.add(tmp);
        }
        return t.get(rowIndex);*/
        //O(k)空间复杂度:
        /*
        List<Integer> t = new java.util.ArrayList<>();
        t.add(1);
        for(int i=1;i<=rowIndex;i++){
            t.set(0,1);
            for(int j=i-1;j>0;j--){           //从前往后会覆盖 应该从后往前
                t.set(j,t.get(j-1)+t.get(j));
            }
            t.add(1);
        }
        return t;*/
        //优雅解法（数学公式法）: 杨辉三角其实是组合数
        List<Integer> ans = new java.util.ArrayList<>();
        int N = rowIndex;
        long pre = 1;
        ans.add(1);
        for (int k = 1; k <= N; k++) {
            long cur = pre * (N - k + 1) / k;       //用long防止计算过程中溢出
            ans.add((int) cur);
            pre = cur;
        }
        return ans;

    }

    /*
    题目121：买卖股票的最佳时机
    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
    注意：你不能在买入股票前卖出股票

    示例 1:
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

    示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public static int maxProfit(int[] prices) {
        //动态规划:以下计算方法可行，但超出内存限制 优化一下
        /*
        int n = prices.length;
        int[][] ans = new int[n][n];
        for(int i=0;i<n;i++){
            ans[i][i]=0;        //当天买入卖出记为收益0
        }
        int max = 0;
        for(int c=1;c<=n-1;c++){
            for(int i=0;i<n-c;i++){
                int j=i+c;
                ans[i][j] = prices[j]-prices[i];
                if(ans[i][j]>max){
                    max = ans[i][j];
                }
            }
        }
        return max;*/
        //下面的dp无意义，还不如暴力解法节约空间。。。
        /*
        if(prices.length==0||prices.length==1){
            return 0;
        }
        int [] dp = new int[prices.length];
        int max = 0;
        dp[0] = 0;          //dp[i]表示第i天卖出的最大收益 dp[i] = max{0,p[i]-p[i-1],p[i]-p[i-2]...}
        for(int i=1;i<prices.length;i++){
            int tmpMax = 0;
            for(int j=i-1;j>=0;j--){
                if(prices[i]-prices[j]>tmpMax){
                    tmpMax = prices[i]-prices[j];
                }
            }
            if(tmpMax>max){
                max = tmpMax;
            }
        }
        return max;*/
        //正确的动态规划:
        /*
        if(prices.length==0||prices.length==1){
            return 0;
        }
        int [][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.min(dp[i-1][1],prices[i]);
        }
        return dp[prices.length-1][0];*/
        //优雅解法O(n)复杂度：在最低点买进，必定是最优的，所以要保存一直以来的最低价。如此可省略内循环
        if(prices.length==0||prices.length==1){
            return 0;
        }
        int maxProfit = 0;
        int minVal = prices[0];
        for(int i=1;i<prices.length;i++){       //i表示在第i天卖出，买进是i之前价格最低的那天。
            if(prices[i]-minVal>maxProfit){     //注意一次交易，先买后卖。
                maxProfit = prices[i]-minVal;
            }                                   //从后往前递推也可以，保存最高价，然后减去当前prices【i】 和maxProfit比较。
            minVal = Math.min(minVal,prices[i]);
        }
        return maxProfit;
    }

    /*
题目122：买卖股票的最佳时机2
设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

示例 2:
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
    public static int maxProfit2(int[] prices) {
        //贪心算法:画出折线图之后一目了然
        int maxProfit = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                maxProfit += (prices[i]-prices[i-1]);
            }
        }
        //return maxProfit;
        //动态规划：
        int [][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /*
    题目167：两数之和2
    给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
示例:

输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     */
    public int[] twoSum2(int[] numbers, int target) {
        //暴力解法两层循环：

        //头尾双指针 O（n）复杂度
        int i=0;
        int j=numbers.length-1;
        while(i<j){
            if(numbers[i]+numbers[j]==target){
                int[] ans = {i+1,j+1};
                return ans;
            }else if(numbers[i]+numbers[j]<target){
                i++;
            }else{
                j--;
            }
        }
        return null;
    }

        /*
    题目169：
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:
输入: [3,2,3]
输出: 3
     */
        public static int count(int a,int[] nums){
            int sum=0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==a){
                    sum++;
                }
            }
            return sum;
        }
        public static int findMajor(int[] nums){
            if(nums.length<=2){
                return nums[0];
            }
            else{
                int len = nums.length/2;
                int[] p1 = new int[len];
                int[] p2 = new int[nums.length-len];
                System.arraycopy(nums,0,p1,0,len);
                System.arraycopy(nums,len,p2,0,nums.length-len);
                int a = findMajor(p1);
                int b = findMajor(p2);
                if(a==b){
                    return a;
                }else{
                    if(count(a,nums)>nums.length/2){
                        return a;
                    }else{
                        return b;
                    }
                }
            }
        }
        public static int majorityElement(int[] nums) {
            //利用哈希表 一次遍历
            Map<Integer,Integer> map = new HashMap<>();
            int n = nums.length/2;
            for(int i=0;i<nums.length;i++){
                if(map.containsKey(nums[i])){
                    map.put(nums[i],map.get(nums[i])+1);
                }else{
                    map.put(nums[i],1);
                }
                if(map.get(nums[i])>n){
                    return nums[i];
                }
            }
            return -1;
            //也可以先排序 然后计算当前元素的n/2后还是不是当前元素判断:

            //可以使用分治 a若为众数则a必定两半中至少其中一个的众数:
            //return findMajor(nums);

        }

        /* 题目189：旋转数组
    给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数

    示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
            */
        public static void rotate(int[] nums, int k) {
            //求余即可
            int n = nums.length;
            int val = k%nums.length;
            int[] tmp = new int[val];
            for(int i=0;i<val;i++){
                tmp[i] = nums[i];
            }
            for(int j=nums.length-1;j>=val;j--){
                nums[(j+val)%nums.length] = nums[j];
            }
            for(int i=0;i<val;i++){
                nums[(i+val)%nums.length] = tmp[i];
            }
        }
        /*
        题目217：判断是否存在重复元素
        给定一个整数数组，判断是否存在重复元素。
        如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
        示例 1:
        输入: [1,2,3,1]
        输出: true
         */
        public static boolean containsDuplicate(int[] nums) {
            //哈希表:
            Map<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<nums.length;i++){
                if(map.containsKey(nums[i])){
                    return true;
                }
                map.put(nums[i],1);
            }
            return false;
            /* HashSet
            Set<Integer> set = new HashSet<>();
            for (int x: nums) {
                if (set.contains(x)) return true;
                set.add(x);
            }
            return false;*/
            //此题也可以先排序 快排最快
        }
        /*
        题目217：判断是否存在重复元素2
        给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
        使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。

        示例 1:
        输入: nums = [1,2,3,1], k = 3
        输出: true
         */
        public static boolean containsNearbyDuplicate(int[] nums, int k) {
            //暴力解法
            for(int i=0;i<nums.length;i++){
                for(int j=i+1;j<nums.length;j++){
                    if(nums[i]==nums[j]&&j-i<=k){
                        return true;
                    }
                }
            }
            //return false;
            //hashset解决 限定容量k个  遍历数组元素然后插入 如果满k个则移除第一个 如果当前元素已经在其中了说明索引差值小于k
            if(k<=0){
                return false;
            }
            HashSet<Integer> set = new HashSet<>(k);
            for(int i=0;i<nums.length;i++){
                if(set.contains(nums[i])){
                    return true;
                }else if(set.size()<k){
                    set.add(nums[i]);
                }else{
                    set.remove(nums[i-k]);
                    set.add(nums[i]);
                }
            }
            return false;
        }

    /*
题目268：缺失数字
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
示例 1:
输入: [3,0,1]
输出: 2
*/
    public int missingNumber(int[] nums) {
        //数学方法做，看看与等差数列的和差距多少
        int max = nums.length;
        int sum = (1+max)*max/2;
        int cnt = 0;
        for(int i=0;i<max;i++){
            cnt+=nums[i];
        }
        int diff = sum-cnt;
        //return diff;
        //法2：先排序 逐个看哪两个相邻元素差值是2
        //法3：hashset  两次遍历
        //法4：位运算：异或满足结合律 且一个数与0异或运算等于它自己
        int len = nums.length;
        int result = len;
        for(int i=0;i<len;i++)
        {
            result ^= i^nums[i];
        }
        return result;
    }

        /*
题目283：移动0
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
示例 1:
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
*/
        public static void moveZeroes(int[] nums) {
            //cnt计数(本质是双指针)--覆盖
            int cnt = 0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==0){
                    cnt++;
                }else{
                    nums[i-cnt] = nums[i];
                }
            }
            for(int j=nums.length-cnt;j<nums.length;j++){
                nums[j] = 0;
            }
            //标准双指针--交换
            int i=0,j=0;
            for(;i<nums.length;i++){
                if(nums[i]!=0){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    j++;
                }
            }
        }

    /*
题目414：第三大的数
给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:
输入: [3, 2, 1]
输出: 1
解释: 第三大的数是 1.
*/

    public int thirdMax(int[] nums) {
        //法1：先排序再找 复杂度超过O(n)
        //法2: 先找最大的 然后找第二大 最后找第三大 理论上是O(3n)  但是要先排除重复 利用hashset
        //法3：首先去重 然后维持一个长度为3的数组
        //其实可以优化 不用hashset去重  维护3个元素的数组 然后直接遍历一次 判断当前和前三大相等则continue
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            try {
                set.add(nums[i]);
            }catch (Exception e){
                continue;
            }

        }
        int[] ans = {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};
        for(Integer i: set){
            if(i>ans[0]){
                ans[2] = ans[1];
                ans[1] = ans[0];
                ans[0] = i;
            }else if(i>ans[1]){
                ans[2] = ans[1];
                ans[1] = i;
            }else if(i>ans[2]){
                ans[2] = i;
            }
        }
        if(set.size()<3){
            return ans[0];
        }
        //return ans[2];
        //维护一个只有3个元素的TreeSet，如果大于三个元素就就把Set中的最小最小值remove掉。
        //最后如果Set中元素小于3就返回Set最大值，否则返回最小值。
        TreeSet<Integer> tree = new TreeSet<>();
        for(int i=0;i<nums.length;i++){
            tree.add(nums[i]);
            if(tree.size()>3){
                tree.remove(tree.first());          //第一个是最小的
            }
        }
        return tree.size()==3?tree.last():tree.first();
    }

    /*
题目448：找到所有数组中消失的数字
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
找到所有在 [1, n] 范围之间没有出现在数组中的数字。
您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例 1:
输入:
[4,3,2,7,8,2,3,1]
输出:
[5,6]
*/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //方法1：利用一个hashMap 两次遍历  可是这样不就利用额外空间了？
        List<Integer> ans = new java.util.ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],nums[i]);
        }
        for(int i=1;i<=nums.length;i++){
            if(!map.containsKey(i)){
                ans.add(i);
            }
        }
        return ans;
        //方法2（很强！！！）：利用index 和 nums[index]的关系 二者都在1-n范围内
        //我们只关心是否有出现，而不关心出现了1次还是2次！

    }

    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit(prices));

    }

}
