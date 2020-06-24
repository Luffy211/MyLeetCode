package MyStructure.MyQueue;

//使用数组实现循环队列
//为了有效区别队列空和队列满，有以下三种方式：
//1、数组不填满，空出一个以做区别
//2、填满数组，但利用一个标志位sign，入队时令sign=1，出队时令sign=0，如果sign==0 且头指针和尾指针指向同一个，则判断为空；为满时相对的sign==1
//3、填满数组，使用count计数，入队时count++，出队时count--
public class ArrayQueue {
    private int[] array;
    static int MaxSize = 6;
    private int head;
    private int rear;
    private int size;
    private int sign;
    /*
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
        if((rear+1)%MaxSize == head){
            return;
        }
        array[rear] = val;
        rear = (rear+1)%MaxSize;
    }

    //出队列，需要判断队列是否为空
    public boolean pop(){
        if(head==rear){
            return false;
        }
        int x = array[head];
        head = (head+1)%MaxSize;
        return true;
    }

    //计算队列内元素的个数
    public int getSize(){
        return (rear+MaxSize-head)%MaxSize;
    }*/
    //方法2：结合sign判断为空或为满
    /*
    ArrayQueue(){
        this.array = new int[MaxSize];
        this.head=0;
        this.rear=0;
        this.sign=0;
    }

    public boolean isEmpty(){
        if(head==rear&&sign==0)
            return true;
        return false;
    }

    public void push(int val){
        if(head==rear&&sign==1){        //队满
            return;
        }
        array[rear] = val;
        rear = (rear+1)%MaxSize;
        sign = 1;
    }

    public boolean pop(){
        if(head==rear&&sign==0){
            return false;
        }
        int x = array[head];
        head = (head+1)%MaxSize;
        sign = 0;
        return true;
    }

    public int getSize(){
        if(sign==1&&rear==head)
            return MaxSize;
        else if(sign==0&&rear==head)
            return 0;
        else return (rear+MaxSize-head)%MaxSize;
    }*/
    //方法3：利用变量size 记录当前队列元素个数
    ArrayQueue(){
        this.array = new int[MaxSize];
        this.rear = 0;
        this.head = 0;
        this.size = 0;
    }

    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }
    public boolean push(int val){
        if(size==MaxSize){
            return false;
        }
        array[rear] = val;
        rear = (rear+1)%MaxSize;
        size++;
        return true;
    }
    public boolean pop(){
        if(size==0)
            return false;
        int x = array[head];
        head = (head+1)%MaxSize;
        size--;
        return true;
    }
    public int getSize(){
        return size;
    }
    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue();
        System.out.println(q.isEmpty());
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.push(6);
        System.out.println(q.getSize());
        q.push(7);
        System.out.println(q.getSize());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
    }
}
