package MyStructure.MyQueue;

//使用数组实现循环队列
//为了有效区别队列空和队列满，有以下三种方式：
//1、数组不填满，空出一个以做区别
//2、填满数组，但利用一个标志位sign，入队时令sign=1，出队时令sign=0，如果sign==0 且头指针和尾指针指向同一个，则判断为空；为满时相对的sign==1
//3、填满数组，使用count计数，入队时count++，出队时count--
public class ArrayQueue {
    int[] array;
    static int MaxSize;
    int head;
    int rear;

    //方法1：
    ArrayQueue(){
        this.array = new int[MaxSize];
        this.head = 0;
        this.rear = 0;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return head==rear;
    }

    //入队列,需要先判断队列是否满了
    public void push(int val){

    }

    //出队列，需要判断队列
}
