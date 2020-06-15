package MyStructure;

//循环单链表：末尾节点的next指针不再是null，而是指向表头
public class ReLinkedList {
    class ReLinkedNode{
        int val;
        ReLinkedNode next;
        ReLinkedNode(int val){
            this.val = val;
        }
        void display(){
            System.out.print(this.val);
        }
    }
    ReLinkedNode head;
    //判断是否为空  这是不含空值头节点的
    public boolean isEmpty(){
        return head==null;
        //return head.next==head;       这是带空值头指针的情况
    }

    //头插法
    //头插法插入元素：
    public void insertFirst(int key){
        ReLinkedNode newNode = new ReLinkedNode(key);
        if(head==null) {
            head = newNode;
            head.next = head;               //只有一个节点时 next也要指向自己
            return;
        }
        //第一步：
        newNode.next = head;
        //第二步：找到末尾的节点 将其next指针指向新的头插节点
        ReLinkedNode tmp = head;
        while(tmp.next!=head){              //注意这个判断表尾的条件
            tmp = tmp.next;
        }
        tmp.next = newNode;
        head = newNode;
    }

}
