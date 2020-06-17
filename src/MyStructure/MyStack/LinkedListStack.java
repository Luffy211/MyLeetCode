package MyStructure.MyStack;

//使用单链表实现栈
//不会出现溢出 克服数组顺序栈的固定大小问题
public class LinkedListStack {
    private class Node{
        int val;
        Node next;
        Node(int key){
            this.val = key;
        }
    }
    private Node head;

    //栈初始化
    LinkedListStack(){
        this.head = null;
    }

    //判断链表是否为空
    public boolean isEmpty(){
        return head==null;
    }

    //进栈：不会出现栈满的情况
    public void push(int key){
        Node newNode = new Node(key);
        if(head==null){
            head = newNode;
            return;
        }else{
            newNode.next = head;
            head = newNode;
            return;
        }
    }

    //出栈:注意栈空的情况
    public void pop(int[] res){
        if(head==null){
            System.out.println("栈空");
            return;
        }
        res[0] = head.val;
        head = head.next;
        return;
    }

    //获取栈顶元素
    public void getTop(int[] get){
        if(head==null){
            System.out.println("栈空");
            return;
        }
        get[0] = head.val;
        return;
    }

    public static void main(String[] args) {
        int[] res = new int[1];
        int[] get = new int[1];
        LinkedListStack stack = new LinkedListStack();
        System.out.println(stack.isEmpty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop(res);
        System.out.println(res[0]);
        stack.pop(res);
        System.out.println(res[0]);
        stack.pop(res);
        System.out.println(res[0]);
    }
}
