package MyStructure;

//双端双链表：双向链表 且有尾指针指向末尾节点
//头结点的prev为空 尾结点的next为空
public class DoubleDLinkedList {
    class DListNode{
        int val;
        DListNode prev;
        DListNode next;
        DListNode(int val){
            this.val = val;
        }
        void display(){
            System.out.print(this.val);
        }
    }
    DListNode head,rear;                  //头尾两个指针
    //判断链表是否为空
    public boolean isEmpty(){
        return head==null;
    }

    //头插法插入元素：
    public void insertFirst(int key){
        DListNode newNode = new DListNode(key);
        if(head==null) {
            head = newNode;
            rear = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }
    //尾插法插入元素：
    public void insertLast(int key){
        DListNode newNode = new DListNode(key);
        if(head==null) {
            head = newNode;
            rear = newNode;
            return;
        }
        rear.next = newNode;
        newNode.prev = rear;
        newNode.next = null;
    }
}
