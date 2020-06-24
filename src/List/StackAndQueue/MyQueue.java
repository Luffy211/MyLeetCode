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
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i=0;i<tasks.length;i++){
                if(map.containsKey(tasks[i])){
                    int tmp = map.get(tasks[i]);
                    map.put(tasks[i],tmp+1);
                }else{
                    map.put(tasks[i],1);
                }
            }
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
            return time;
        }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        char[] task = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        System.out.println(q.leastInterval(task,2));
    }
}
