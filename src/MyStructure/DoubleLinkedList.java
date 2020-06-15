package MyStructure;

//双端单链表，是单向链表而不是双向链表
//除了头指针外，还有一个尾指针指向尾节点
//常用作队列的实现，表尾插入，因为有尾指针所有O（1）即可
//常用的操作和单链表类似
public class DoubleLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
        void display(){
            System.out.print(this.val);
        }
    }
    ListNode head,rear;                     //头和尾两个节点

    //判断链表是否为空
    public boolean isEmpty(){
        return head==null;
    }

    //头插法插入元素：
    public void insertFirst(int key){
        ListNode newNode = new ListNode(key);
        if(head==null) {
            head = newNode;
            rear = newNode;             //只有一个节点时头尾指向同一个
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    //尾插法插入元素：
    //常用来实现队列
    public void insertLast(int key){
        ListNode newNode = new ListNode(key);
        if(head==null) {
            head = newNode;
            rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }
}
