package MyStructure.MyLinkedList;

//双向链表 每个节点有prev和next两个指针
//头结点的prev为null  尾结点的next为null
public class DLinkedList {
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
    DListNode head;                  //单链表只有指向链表头的指针
    //判断链表是否为空
    public boolean isEmpty(){
        return head==null;
    }

    //头插法插入元素：
    public void insertFirst(int key){
        DListNode newNode = new DListNode(key);
        if(head==null) {
            head = newNode;
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
            return;
        }
        DListNode tmp = head;
        while(tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = newNode;
        newNode.prev = tmp;
    }
    //在链表的指定位置i 插入元素
    ///假设index从1开始 且index一定有效   index的范围[1,len+1]
    ////法1：找到第i-1个元素    PS:index==1时单独处理
    public void insertByIndex(int index,int key){
        DListNode newNode = new DListNode(key);
        if(index==1){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        DListNode tmp = head;
        for(int i=1;i<index-1;i++){
            tmp = tmp.next;
        }
        try {
            tmp.next.prev = newNode;            //这句话放第一条
            newNode.next = tmp.next;
            tmp.next = newNode;
            newNode.prev = tmp;
        }catch (Exception e){
            newNode.next = null;
            newNode.prev = tmp;
            tmp.next = newNode;
            System.out.println("插入的是末尾位置");
        }
    }
    //给定元素 删除该链表节点 假设链表元素不重复
    public void deleteByKey(int key){
        if(head==null)
            return;
        if(head.val==key){
            head.next.prev = null;
            head = head.next;
            return;
        }
        DListNode tmp = head.next;
        while(tmp.val!=key&&tmp!=null){
            tmp = tmp.next;
        }
        if(tmp.val==key){
            try {
                tmp.next.prev = tmp.prev;           //末尾节点时 会报空指针异常
                tmp.prev.next = tmp.next;
            }catch (Exception e){
                tmp.prev.next = null;
                System.out.println("删除的是末尾元素");
            }
            return;
        }
    }
    //删除指定位置i的节点
    ///i是末尾节点节点时 try catch
    public void deleteByIndex(int index){
        if(index==1){
            head = head.next;
            return;
        }
        DListNode current = head.next;
        for(int i =2;i<index;i++){
            current = current.next;
        }
        try {
            current.next.prev = current.prev;
            current.prev.next = current.next;
        }catch (Exception e){
            current.prev.next = null;
            System.out.println("删除的是末尾节点");
        }
    }
    //返回链表的长度值
    public int countLength(){
        if(head==null)
            return 0;
        int len = 0;
        DListNode tmp = head;
        while(tmp!=null){
            len++;
            tmp = tmp.next;
        }
        return len;
    }

    //打印单链表
    public void printList(){
        if(head==null){
            System.out.println("List is null!");
            return;
        }
        DListNode tmp = head;
        while(tmp!=null){
            tmp.display();
            System.out.print(" ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DLinkedList dList = new DLinkedList();
        dList.insertFirst(1);
        dList.insertFirst(2);
        dList.insertFirst(3);
        dList.insertFirst(4);
        dList.insertFirst(5);
        dList.printList();
        dList.insertLast(8);
        dList.printList();
        dList.insertByIndex(6,9);
        dList.printList();
        dList.deleteByKey(8);
        dList.printList();
        dList.deleteByIndex(6);
        dList.printList();
        System.out.println(dList.countLength());
    }
}
