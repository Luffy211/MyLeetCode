package MyStructure.MyLinkedList;

//实现单链表，其中节点类作为单链表类的内部类实现
//头插法可用于实现栈
public class LinkedList {
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
    ListNode head;                  //单链表只有指向链表头的指针
    LinkedList(){
        head = null;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return head==null;
    }
    //头插法插入元素：
    public void insertFirst(int key){
        ListNode newNode = new ListNode(key);
        if(head==null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    //尾插法插入元素：
    public void insertLast(int key){
        ListNode newNode = new ListNode(key);
        if(head==null) {
            head = newNode;
            return;
        }
        ListNode tmp = head;
        while(tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }
    //在链表的指定位置i 插入元素
    ///假设index从1开始 且index一定有效   index的范围[1,len+1]
    ////法1：找到第i-1个元素    PS:index==1时单独处理
    ////法2：找到第i个元素，插到第i+1位置，然后swap节点i和节点i+1的值  PS:index==len+1时不适用
    public void insertByIndex(int index,int key){
        ListNode newNode = new ListNode(key);
        if(index==1){
            newNode.next = head;
            head = newNode;
            return;
        }
        ListNode tmp = head;
        for(int i=1;i<index-1;i++){
            tmp = tmp.next;
        }
        newNode.next = tmp.next;
        tmp.next = newNode;
    }
    //给定元素 删除该链表节点 假设链表元素不重复
    ///法1：双指针
    public void deleteByKey_1(int key){
        if(head==null)
            return;
        if(head.val==key){
            head = head.next;
            return;
        }
        ListNode front = head;
        ListNode current = head.next;
        while(current.val!=key&&current!=null){
            front = current;
            current = current.next;
        }
        if(current.val==key){
            front.next = current.next;
            return;
        }
    }
    //给定元素 删除该链表节点 假设链表元素不重复
    ///法2：next节点赋值法   PS：对于尾结点不好处理
    public void deleteByKey_2(int key) {
        if(head==null)
            return;
        if (head.val == key) {
            head = head.next;
            return;
        }
        ListNode tmp = head.next;
        while(tmp.val!=key&&tmp!=null){
            tmp = tmp.next;
        }
        if(tmp.val==key&&tmp.next!=null){
            tmp.val = tmp.next.val;
            tmp.next = tmp.next.next;
        }
        if(tmp.val==key&&tmp.next==null){
            System.out.println("此方法删除尾节点失败！！！");
            return;
        }
    }
    //删除指定位置i的节点
    ///方法1：找到第i-1个节点
    ///方法2：找到低i个节点，将i+1的值赋予它，然后i指向i+2  PS：i不为末尾
    public void deleteByIndex(int index){
        if(index==1){
            head = head.next;
            return;
        }
        ListNode front = head;
        ListNode current = head.next;
        for(int i =1;i<index-1;i++){
            front = current;
            current = current.next;
        }
        front.next = current.next;
    }
    //返回单链表的长度值
    public int countLength(){
        if(head==null)
            return 0;
        int len = 0;
        ListNode tmp = head;
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
        ListNode tmp = head;
        while(tmp!=null){
            tmp.display();
            System.out.print(" ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        list1.insertLast(1);
        list1.insertLast(2);
        list1.insertLast(3);
        list1.insertLast(4);
        list1.insertLast(5);
        list1.insertFirst(9);
        list1.printList();
        list1.insertByIndex(7,6);
        list1.printList();
        System.out.println("len is: "+list1.countLength());
        //list1.deleteByKey_1(9);
        list1.deleteByKey_2(6);
        list1.printList();
        list1.deleteByIndex(1);
        list1.printList();
    }
}
