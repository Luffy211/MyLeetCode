package MyStructure.MyQueue;

import java.util.LinkedList;
import java.util.Queue;

//使用双端队列实现
public class LinkedListQueue {
    private class Node{
        int value;
        Node next;
        Node(){
            value = 0;
            next = null;
        }
    }
    Node head;
    Node rear;
    //初始化
    LinkedListQueue(){
        this.head=null;
        this.rear=null;

    }
    //判断是否为空
    public boolean isEmpty(){
        return head==null&&rear==null;
    }
    //入队列
    public void push(int val){
        Node newNode = new Node();
        newNode.value = val;
        if(head==null&&rear==null){
            head = newNode;         //队列初始为空时 入队特殊处理
            rear = newNode;
        }else {
            rear.next = newNode;
            rear = newNode;
        }
    }
    //出队列
    public boolean pop(){
        if(head==null&&rear==null)
            return false;
        int x = head.value;
        if(head==rear){             //队列只有一个节点时 出队特殊处理
            head=null;
            rear=null;
        }else {
            head = head.next;
        }
        return true;
    }
    //求队列长
    public int getSize(){
        if(head==null&&rear==null)
            return 0;
        int cnt=0;
        Node tmp = head;
        while(tmp!=null){
            tmp = tmp.next;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        /*
        LinkedListQueue q = new LinkedListQueue();
        System.out.println(q.isEmpty());
        q.push(1);
        q.push(2);
        System.out.println(q.isEmpty());
        System.out.println(q.getSize());
        q.pop();
        q.push(3);
        System.out.println(q.getSize());
        q.pop();
        q.pop();
        System.out.println(q.isEmpty());*/
        //JAVA内置队列：
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);         //入队列
        q.offer(2);
        System.out.println(q.poll());               //出队
        System.out.println(q.peek());               //返回队列第一个元素但不会队
        System.out.println(q.size());               //返回元素个数
    }
}
