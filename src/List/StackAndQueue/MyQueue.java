package List.StackAndQueue;

import java.util.*;

public class MyQueue {
    /*
    933. 最近的请求次数
    写一个 RecentCounter 类来计算最近的请求。
    它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。
    返回从 3000 毫秒前到现在的 ping 数。
    任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。
    保证每次对 ping 的调用都使用比之前更大的 t 值。
     */
    class RecentCounter {
        /*
        Deque<Integer> q;
        public RecentCounter() {
            this.q = new LinkedList<Integer>();
        }
        public int ping(int t) {
            q.addFirst(t);
            int res = 0;
            int start = t-3000;
            for(int x:q){
                if(x>=start&&x<=t){
                    res++;
                }
                if(x<start){
                    break;
                }
            }
            return res;
        }*/
        Queue<Integer> q;
        public RecentCounter() {
            this.q = new LinkedList<>();
        }
        public int ping(int t) {
            q.offer(t);
            while(q.peek()<t-3000){
                q.poll();
            }
            return q.size();
        }
    }
        /*
    621. 任务调度器
    给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
你需要计算完成所有任务所需要的最短时间。
    示例 ：
输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
     */
        public static char getMaxFreq(Queue<Character>q,HashMap<Character,Integer> map){
            char res = '0';
            int maxFreq = 0;
            for(Character key : map.keySet()){
                if(map.get(key)>maxFreq&&!q.contains(key)){
                    maxFreq = map.get(key);
                    res = key;
                }
            }
            return res;
        }
        public int leastInterval(char[] tasks, int n) {
            if(tasks.length==0)
                return 0;
            if(n==0)
                return tasks.length;
            //方法1：1.找出task中的任务种类数 2.利用hashmap保存 3.然后按任务频率降序插入队列 如果该任务cd了 则递归找第二频繁的任务（任务剩余必须大于0）
            //超出时间限制。。。
            //修改成维护一个递增栈
            /*
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i=0;i<tasks.length;i++){
                if(map.containsKey(tasks[i])){
                    int tmp = map.get(tasks[i]);
                    map.put(tasks[i],tmp+1);
                }else{
                    map.put(tasks[i],1);
                }
            }*/
            /*
            List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Character,Integer>>() {
                public int compare(Map.Entry<Character,Integer> o1, Map.Entry<Character, Integer> o2) {
                    return o1.getValue() > o2.getValue() ? 1:-1;  //升序排列
                }
            });
            Stack<Character>stack = new Stack<>();
            for (Map.Entry<Character, Integer> e: list) {
                System.out.println(e.getKey() + ": " + e.getValue());
                stack.push(e.getKey());
            }
            */
            /*
            Queue<Character> taskSeq = new LinkedList<>();
            int len = tasks.length;
            int time = 0;
            while(len>0){

                char key = getMaxFreq(taskSeq,map);
                if(key=='0'){
                    taskSeq.offer(key);
                    time++;
                }else{
                    taskSeq.offer(key);
                    map.put(key,map.get(key)-1);
                    time++;
                    len--;
                }
                if(taskSeq.size()>n){
                    taskSeq.poll();
                }
                System.out.println(taskSeq);
            }
            return time;*/
            //方法2：统计任务集中每个任务的出现频率，任何排序；每次从中选择n+1个最频繁任务，然后频率-1，再排序；重复至频率皆为0
            int[] freq = new int[26];
            for(char x:tasks){
                freq[x-'A'] ++;
            }
            Arrays.sort(freq);
            int time = 0;
            while(freq[25]>0){
                int i = 0;
                while(i<=n){
                    if(freq[25]==0) {
                        break;
                    }
                    if(i<26&&freq[25-i]>0){
                        freq[25-i]--;
                    }
                    time++;
                    i++;
                }
                Arrays.sort(freq);
            }
            return time;
        }

    /*

622. 设计循环队列
设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
你的实现应该支持如下操作：
MyCircularQueue(k): 构造器，设置队列长度为 k 。
Front: 从队首获取元素。如果队列为空，返回 -1 。
Rear: 获取队尾元素。如果队列为空，返回 -1 。
enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
isEmpty(): 检查循环队列是否为空。
isFull(): 检查循环队列是否已满。
 */

    class MyCircularQueue {
        int [] array;
        int head;
        int rear;
        int size;
        int maxSize;
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.array = new int[k];
            this.head=0;
            this.rear=0;
            this.size=0;
            this.maxSize = k;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(size==maxSize)
                return false;
            array[rear] = value;
            rear = (rear+1)%maxSize;
            size++;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(size==0)
                return false;
            int x = array[head];
            head = (head+1)%maxSize;
            size--;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if(size==0)
                return -1;
            return array[head];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(size==0)
                return -1;
            return array[(rear+maxSize-1)%maxSize];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            if(size==0)
                return true;
            return false;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            if(size==maxSize)
                return true;
            return false;
        }
    }

    /*
641. 设计循环双端队列
设计实现双端队列。
你的实现需要支持以下操作：
MyCircularDeque(k)：构造函数,双端队列的大小为k。
insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
isEmpty()：检查双端队列是否为空。
isFull()：检查双端队列是否满了。
*/
    class MyCircularDeque {
        int[] array;
        int head;
        int rear;
        int size;
        int maxSize;
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.array = new int[k];
            this.maxSize = k;
            this.head=0;
            this.rear=0;
            this.size=0;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if(size==maxSize)
                return false;
            if(size==0){                                            //如果队列为空 则插入第一个位置 rear后移一个单位 head不动
                array[head] = value;
                rear = (rear+1)%maxSize;
            }else {
                array[(head + maxSize - 1) % maxSize] = value;
                head = (head + maxSize - 1) % maxSize;                //头指针往前移动;
            }
            size++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if(size==maxSize)
                return false;
            if(size==0){                                            //如果队列为空 则插入第一个位置 rear后移一个单位 head不动
                array[head] = value;
                rear = (rear+1)%maxSize;
            }else {
                array[rear] = value;
                rear = (rear + 1) % maxSize;                //尾指针往后移动;
            }
            size++;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if(size==0)
                return false;
            int x = array[head];
            head = (head +  1) % maxSize;                //头指针往后移动;
            size--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if(size==0)
                return false;
            int x = array[(rear+maxSize-1)%maxSize];
            rear = (rear+maxSize-1)%maxSize;               //尾指针往前移动;
            size--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if(size==0)
                return -1;
            return array[head];
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if(size==0)
                return -1;
            return array[(rear+maxSize-1)%maxSize];
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size==0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size==maxSize;
        }
    }

    /*
面试题 17.09. 第 k 个数
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，
而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21, 25, 35, 49,
示例 1:
输入: k = 5
输出: 9
*/
    //1  3  6  12  24:是等比数列
    public int getKthMagicNumber(int k) {
        if(k==1)
            return 1;
        /*
        int sign=0;
        int sum=1;
        for(int i=1;;i++){
            if(k<sum+3*Math.pow(2,i-1)){
                sign = i;
                break;
            }else{
                sum += 3*Math.pow(2,i-1);
            }
        }
        int index = k - sum;
        int lastMax = (int)(Math.pow(7,index-1))
        int len = (int)(3*Math.pow(2,sign-1));
        HashSet<Integer>set = new HashSet<>();
        for(int j=0;j<sign;j++){

        }*/
        //动态规划：难理解  三指针  丑数列递增
        int p3=0;
        int p5=0;
        int p7=0;
        int[] dp = new int[k];
        dp[0] = 0;
        for(int i=1;i<k;i++){
            dp[i] = Math.min(dp[p3]*3,Math.min(dp[p5]*5,dp[p7]*7));
            if(dp[i]==dp[p3]*3) p3++;
            if(dp[i]==dp[p5]*5) p5++;
            if(dp[i]==dp[p7]*7) p7++;               //3个独立if  避免了插入重复值
        }
        return dp[k-1];
        //方法2：使用堆 + set 也可以解决（比较暴力）
    }
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        char[] task = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        System.out.println(q.leastInterval(task,2));
    }
}
