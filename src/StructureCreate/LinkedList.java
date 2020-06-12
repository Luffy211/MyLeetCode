package StructureCreate;
//java创建链表，并实现相关操作函数

//链表节点类
//基本上链表操作都有递归和循环两种实现
class ListNode{
    int val;            //链表值
    ListNode next;      //指向下一个节点的引用（相当于指针）

    ListNode(){         //无参构造函数，用于建立头结点
    }

    ListNode(int x){    //构造函数
        this.val = x;
    }

    //尾插法插入新的节点
    public void afterAddNode(int newVal){
        if(this.next==null){
            ListNode newNode = new ListNode(newVal);
            this.next = newNode;
        }else{
            this.next.afterAddNode(newVal);         //递归移动至链表尾部插入
        }
    }

    //头插法插入新的节点
    public void frontAddNode(int newVal){
        ListNode newNode = new ListNode(newVal);
        newNode.next = this.next;
        this.next = newNode;                //顺序不能错
    }


    //根据值删除某节点,需要记录当前节点的前一个节点！
    public void removeNodeByVal(int rmVal){
        ListNode front = this;
        ListNode tmp = this.next;
        System.out.println(tmp.val);
        while(tmp!=null){
            if(tmp.val==rmVal){
                front.next = tmp.next;
            }else{
                front = tmp;
            }
            tmp = tmp.next;             //移动至下一个节点
        }
        return;

    }

    //遍历输出链表
    public void printLinkedList(){
        //假设带头结点
        ListNode tmp = this.next;
        while(tmp!=null){
            System.out.print(tmp.val);
            if(tmp.next!=null)
                System.out.print("-->");
            tmp = tmp.next;
        }
    }

    //合并两个有序数组,结果保存至l1
    public static ListNode merge(ListNode l1,ListNode l2){
        //l1 l2带不带头指针是一个问题
        //假设都带
        ListNode front = l1;
        ListNode tmp1 = l1.next;
        ListNode tmp2 = l2.next;
        while(tmp1!=null&&tmp2!=null){
            if(tmp1.val<=tmp2.val){
                front.next = tmp1;
                tmp1 = tmp1.next;
            }else{
                front.next = tmp2;
                tmp2 = tmp2.next;
            }
            front = front.next;
            System.out.println(front.val);
        }
        if(tmp1!=null)
            front.next = tmp1;
        if(tmp2!=null)
            front.next = tmp2;
        return l1;
    }
}
public class LinkedList {
    public static ListNode head;            //链表的头结点  不存储数据
    public static void main(String[] args) {
        /*
        head = new ListNode();
        head.afterAddNode(4);
        head.afterAddNode(9);
        head.afterAddNode(3);
        head.afterAddNode(7);
        head.frontAddNode(0);
        head.frontAddNode(1);
        head.frontAddNode(2);
        head.printLinkedList();
        head.removeNodeByVal(2);
        head.printLinkedList();
        */
        ListNode l1 = new ListNode();
        l1.afterAddNode(3);
        l1.afterAddNode(5);
        l1.afterAddNode(7);
        l1.afterAddNode(9);
        ListNode l2 = new ListNode();
        l2.afterAddNode(4);
        l2.afterAddNode(6);
        l2.afterAddNode(8);
        l2.afterAddNode(10);
        l1.printLinkedList();
        System.out.println();
        l2.printLinkedList();

        System.out.println();
        l1 = ListNode.merge(l1,l2);
        l1.printLinkedList();
    }
}
