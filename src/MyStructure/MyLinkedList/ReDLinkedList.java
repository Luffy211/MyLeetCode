package MyStructure.MyLinkedList;

//循环双链表：表头的prev指向末尾节点 末尾节点的next指向表头节点
public class ReDLinkedList {
    class ReDLinkedNode{
        int val;
        ReDLinkedNode prev;
        ReDLinkedNode next;
        ReDLinkedNode(int val){
            this.val = val;
        }
        void display(){
            System.out.print(this.val);
        }
    }
    ReDLinkedNode head;
    //判断是否为空  这是不含空值头节点的
    public boolean isEmpty(){
        return head==null;
        //return head.next==head;       这是带空值头指针的情况
    }

    //头插法
    //头插法插入元素：
    public void insertFirst(int key){
        ReDLinkedNode newNode = new ReDLinkedNode(key);
        if(head==null) {
            head = newNode;
            head.next = head;               //只有一个节点时 next也要指向自己
            head.prev = head;               //只有一个节点时 prev也要指向自己
            return;
        }
        //第一步：
        newNode.next = head;
        head.prev = newNode;
        //第二步：找到末尾的节点
        ReDLinkedNode tmp = head;
        while(tmp.next!=head){              //注意这个判断表尾的条件
            tmp = tmp.next;
        }
        tmp.next = newNode;
        newNode.prev = tmp;
        head = newNode;
    }
}
