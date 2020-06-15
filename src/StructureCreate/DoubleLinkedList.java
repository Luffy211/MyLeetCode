package StructureCreate;

/*双端链表--比普通链表多了一个指向最后一个节点的引用
 * 特点: 链表可以进行尾巴插入--输出顺序和输入顺序一致
 *      但是不可以进行尾巴删除因为没有倒数第二节点的引用
 * */

class DoubleListNode{
    int val;
    DoubleListNode pre;
    DoubleListNode next;
    DoubleListNode(){
        pre = null;
        next = null;
    }
    DoubleListNode(int val){
        this.val = val;
        pre = null;
        next = null;
    }
}

public class DoubleLinkedList {
    DoubleListNode first;
    DoubleListNode last;
    DoubleLinkedList(){
        first = null;
        last = null;
    }

    //判断是否为空
    public boolean isEmpty(){

        return false;
    }

    //头插入的时候注意空链表对last的处理
    public void insertFirst(int key){

    }

    //尾插入的时候注意空链表对first的处理
    public void insertLast(int key){

    }

    //删除时注意链表只有一个节点时对last的处理
    public DoubleListNode deleteFirst(){

        return null;
    }

    //删除时注意链表只有一个节点时对last的处理
    public DoubleListNode deleteByKey(int key){

        return null;
    }

    //遍历链表并输出
    public void printList(){

    }

    public static void main(String[] args) {

    }
}
