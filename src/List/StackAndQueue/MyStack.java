package List.StackAndQueue;
import java.util.*;

public class MyStack {

    public static boolean isValid(String s) {
        char[] str = s.toCharArray();
        if(str.length<=1)
            return false;
        Stack stack = new Stack();
        for(char x : str){
            if(x=='('||x=='['||x=='{'){
                stack.push(x);
                continue;
            }
            char t = 'a';
            if(!stack.empty()&&stack.peek() instanceof Character)
                    t = (Character) stack.pop();
            else
                return false;

            if((x==')'&&t=='(')||(x==']'&&t=='[')||(x=='}'&&t=='{')) {
                continue;
            }
            else
                return false;
        }
        if(stack.empty())
            return true;
        else
            return false;
    }


    /*
    class MinStack {
        List<Integer> array;
        int topIndex;
        int minVal;

        public MinStack() {
            array = new ArrayList<>();
            this.topIndex = -1;
            this.minVal = Integer.MAX_VALUE;
        }

        public void push(int x) {
            array.add(++topIndex,x);
            if(topIndex==0)
                this.minVal = x;
            else {
                if(x<this.minVal)
                    this.minVal = x;
            }
        }

        public void pop() {
            if(array.get(topIndex)!=minVal)
                topIndex--;
            else{
                topIndex--;
                if(topIndex>=0){
                    int min = array.get(0);
                    for(int i=1;i<=topIndex;i++){
                        if(array.get(i)<min)
                            min = array.get(i);
                    }
                    this.minVal = min;
                }
            }
        }

        public int top() {
            System.out.println(topIndex);
            return array.get(topIndex);
        }

        public int getMin() {
            return this.minVal;
        }
    }
*/
    //利用一个最小栈 优雅解决问题：
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> min_stack;
        public MinStack() {
            stack = new Stack<>();
            min_stack = new Stack<>();
        }
        public void push(int x) {
            stack.push(x);
            if(min_stack.isEmpty() || x <= min_stack.peek())
                min_stack.push(x);
        }
        public void pop() {
            if(stack.pop().equals(min_stack.peek()))
                min_stack.pop();
        }
        public int top() {
            return stack.peek();
        }
        public int getMin() {
            return min_stack.peek();
        }
    }

    /*
    225.使用队列实现栈


    class MyStack {
        Queue<Integer> q1;
        Queue<Integer> q2;

        public MyStack() {
            q1 = new LinkedList<Integer>();
            q2 = new LinkedList<Integer>();
        }

        public void push(int x) {
            if((q1.size()==0&&q2.size()==0)||(q1.size()!=0&&q2.size()==0)){
                q1.offer(x);
                return;
            }
            if(q1.size()==0&&q2.size()!=0){
                q2.offer(x);
                return;
            }
        }

        public int pop() {
            if(q1.size()>0&&q2.size()==0){
                int len = q1.size();
                for(int i=0;i<len-1;i++){
                    q2.offer(q1.poll());
                }
                return q1.poll();
            }
            if(q1.size()==0&&q2.size()>0){
                int len = q2.size();
                for(int i=0;i<len-1;i++){
                    q1.offer(q2.poll());
                }
                return q2.poll();
            }
            return 1;
        }

        public int top() {
            if(q1.size()>0&&q2.size()==0){
                int len = q1.size();
                for(int i=0;i<len-1;i++){
                    q2.offer(q1.poll());
                }
                int tmp =  q1.poll();
                q2.offer(tmp);
                return tmp;
            }
            if(q1.size()==0&&q2.size()>0){
                int len = q2.size();
                for(int i=0;i<len-1;i++){
                    q1.offer(q2.poll());
                }
                int tmp =  q2.poll();
                q1.offer(tmp);
                return tmp;
            }
            return 0;
        }

        public boolean empty() {
            return q1.size()==0&&q2.size()==0;
        }
    }
    */

    /*232.使用栈实现队列*/
    class MyQueue {
        Stack<Integer> s1;
        Stack<Integer> s2;

        /** Initialize your data structure here. */
        public MyQueue() {
            s1 = new Stack<Integer>();
            s2 = new Stack<Integer>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if((s1.empty()&&s2.empty())||(!s1.empty()&&s2.empty())){
                s1.push(x);
            }
            if(s1.empty()&&!s2.empty()) {
                int len = s2.size();
                for (int i = 0; i < len; i++) {
                    s1.push(s2.pop());
                }
                s1.push(x);
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(!s1.empty()){
                int len = s1.size();
                for(int i=0;i<len-1;i++){
                    s2.push(s1.pop());
                }
                return s1.pop();
            }
            if(!s2.empty()){
                return s2.pop();
            }
            return 0;                   //假定了非空，此处不会执行
        }

        /** Get the front element. */
        public int peek() {
            if(!s1.empty()){
                int len = s1.size();
                for(int i=0;i<len-1;i++){
                    s2.push(s1.pop());
                }
                int tmp =  s1.pop();
                s2.push(tmp);
                return tmp;
            }
            if(!s2.empty()){
                return s2.peek();
            }
            return 0;                   //假定了非空，此处不会执行
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return s1.empty()&&s2.empty();
        }
    }

    /*496.下一个更大元素i
    给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
    nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

    输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
    输出: [-1,3,-1]
    解释:
    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。

    输入: nums1 = [2,4], nums2 = [1,2,3,4].
    输出: [3,-1]
    解释:
   对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //暴力法：两层循环 O(n^2)
        /*
        int[] res = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            int sign = 0;
            int chg = 0;
            for(int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    sign = 1;
                }
                if(nums1[i]<nums2[j]&&sign==1){
                    res[i] = nums2[j];
                    chg = 1;
                    break;
                }
            }
            if(chg==0){
                res[i] = -1;
            }
        }
        return res;*/
        //使用栈解决:维护一个单调栈
        Stack<Integer> s = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<nums2.length;i++){
            if(s.empty()||nums2[i]<s.peek()){
                s.push(nums2[i]);
            }else{
                while(!s.empty()&&nums2[i]>s.peek()) {
                    map.put(s.pop(), nums2[i]);
                }
                s.push(nums2[i]);
            }
        }
        while(!s.empty()){
            map.put(s.pop(), -1);
        }
        int[] res = new int[nums1.length];
        for(int j=0;j<nums1.length;j++){
            res[j] = map.get(nums1[j]);
        }
        return res;

        //方法3:不使用栈 利用hashmap 先从后往前遍历nums2 找出每个元素的对应值
    }

    /*
    682.棒球比赛:
    你现在是棒球比赛记录员。
    给定一个字符串列表，每个字符串可以是以下四种类型之一：
1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
你需要返回你在所有回合中得分的总和。
     */
    public int calPoints(String[] ops) {
        Stack<Integer> s = new Stack<>();
        for(String op : ops){
            if(op.equals("+")){
                int num1 = s.pop();
                int num2 = s.peek();
                s.push(num1);
                s.push(num1+ num2);
                continue;
            }
            if(op.equals("C")){
                s.pop();
                continue;
            }
            if(op.equals("D")){
                int num = s.peek();
                s.push(2*num);
                continue;
            }
            s.push(Integer.parseInt(op));
        }
        int res = 0;
        while(!s.empty()){
            res+=s.pop();
        }
        return res;
    }
    /*
    844. 比较含退格的字符串
    给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
    注意：如果对空文本输入退格字符，文本继续为空。
     */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        char[] str1 = S.toCharArray();
        char[] str2 = T.toCharArray();
        for(char x: str1){
            if(x=='#'){
                if(!s1.empty())
                    s1.pop();
            }else{
                s1.push(x);
            }
        }
        for(char x: str2){
            if(x=='#'){
                if(!s2.empty())
                    s2.pop();
            }else{
                s2.push(x);
            }
        }
        if(s1.size()!=s2.size())
            return false;
        int len = s1.size();
        for(int i=0;i<len;i++){
            if(s1.pop()!=s2.pop())
                return false;
        }
        return true;
    }

    /*
    1021. 删除最外层的括号
    有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
    如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
    给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
    对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
     */
    public String removeOuterParentheses(String S) {
        if(S.equals(""))
            return "";
        Stack<Character>s = new Stack<>();
        char[] str = S.toCharArray();
        String res = "";
        int len = str.length;
        s.push(str[0]);
        int lSign = 0;
        int rSign = 0;
        for(int i=1;i<len;i++){
            if(str[i]=='(')
                s.push(str[i]);
            else{
                s.pop();
            }
            if(s.empty()) {
                rSign = i;
                res += new String(Arrays.copyOfRange(str,lSign+1,rSign));
                lSign = i+1;
            }
        }
        return res;
        //优雅实现:
        /*
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for (char c : S.toCharArray()) {
            if (c == ')') --level;
            if (level >= 1) sb.append(c);
            if (c == '(') ++level;
        }
        return sb.toString();*/

        /*
        StringBuilder sb=new StringBuilder();
        int mark=-1;
        for(int i=0;i<S.length();i++){
            char c=S.charAt(i);
            if(c=='('){
                mark++;
            }
            if(c==')'){
                mark--;
            }
             if(mark==0&&c=='('||mark==-1&&c==')'){
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
         */
    }

        /*
    1047. 删除字符串中的所有相邻重复项
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
在 S 上反复执行重复项删除操作，直到无法继续删除。
在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
    输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     */
        public String removeDuplicates(String S) {
            //方法1：使用栈
            /*
            if(S.length()<=1)
                return S;
            Stack<Character>stack = new Stack<>();
            for(int i=0;i<S.length();i++){
                if(stack.empty()){
                    stack.push(S.charAt(i));
                }else{
                    if(stack.peek()==S.charAt(i)){
                        stack.pop();
                    }else{
                        stack.push(S.charAt(i));
                    }
                }
            }
            if(stack.empty())
                return "";
            char[] res = new char[stack.size()];        //注意这个操作
            for(int i=res.length-1;i>=0;i--){
                res[i] = stack.pop();
            }
            return new String(res);*/
            //方法2:利用辅助数组 双指针遍历S
            int[] sup = new int[S.length()];        //sup[i]==0 表示第i个字符要保留 sup[i]==-1表示舍弃
            int l = 0;          //慢指针
            for(int r=1;r<S.length();){
                if(S.charAt(l)==S.charAt(r)){
                    sup[l] = -1;
                    sup[r] = -1;
                    int sign = 0;
                    for(int k=l-1;k>=0;k--){
                        if(sup[k]!=-1){
                            l = k;
                            sign = 1;
                            break;
                        }
                    }
                    if(sign==0){            //说明往前皆为-1
                        l = r+1;
                        r = r+2;
                    }else{
                        r++;
                    }
                }else{
                    //找到下一个sup值不为-1的位置
                    for(int k=l+1;k<=r;k++){
                        if(sup[k]!=-1) {
                            l = k;
                            break;
                        }
                    }
                    r++;
                }
            }
            StringBuilder ss = new StringBuilder();
            for(int i =0;i<sup.length;i++){
                if(sup[i]!=-1){
                    ss.append(S.charAt(i));
                }
            }
            return ss.toString();
            //方法3：使用递归
        }
        /*
1441. 用栈操作构建数组
给你一个目标数组 target 和一个整数 n。每次迭代，需要从  list = {1,2,3..., n} 中依序读取一个数字。
请使用下述操作来构建目标数组 target ：
Push：从 list 中读取一个新元素， 并将其推入数组中。
Pop：删除数组中的最后一个元素。
如果目标数组构建完成，就停止读取更多元素。
题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
请返回构建目标数组所用的操作序列。
题目数据保证答案是唯一的。

输入：target = [1,3], n = 3
输出：["Push","Push","Pop","Push"]
解释：
读取 1 并自动推入数组 -> [1]
读取 2 并自动推入数组，然后删除它 -> [1]
读取 3 并自动推入数组 -> [1,3]

   */
        public List<String> buildArray(int[] target, int n) {
            List<String>res = new ArrayList<>();
            for(int i=0;i<target[0]-1;i++){
                res.add("Push");
                res.add("Pop");
            }
            res.add("Push");
            for(int i=1;i<target.length;i++){
                int tmp = target[i] - target[i-1];
                for(int j=0;j<tmp-1;j++){
                    res.add("Push");
                    res.add("Pop");
                }
                res.add("Push");
            }
            return res;
        }
    /*
面试题59 - I. 滑动窗口的最大值
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
示例:
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

*/
    public int[] maxSlidingWindow(int[] nums, int k) {
        //暴力法：先计算出有效窗口数 并建立数组 然后遍历每个窗口求窗口的最大值
        if(nums.length==0)
            return nums;
        int len = nums.length;
        int newLen = len-k+1;
        int [] ans = new int[newLen];
        for(int i=0;i<newLen;i++){
            int max = Integer.MIN_VALUE;
            for(int j=i;j<i+k;j++){
                if(nums[j]>max){
                    max = nums[j];
                }
            }
            ans[i] = max;
        }
        return ans;
        //使用栈：

    }

    public static void main(String[] args) {
        String s = "()";
        //System.out.println(isValid(s));
        List<Integer> list = new ArrayList<>();
        list.add(0,2);
        list.add(1,3);
        System.out.println(list.get(0));

    }
}
