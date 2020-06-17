package MyStructure.MyStack;

//使用数组的顺序存取实现栈
public class ArrayStack {
    private int[] store;
    private int top;
    private int maxSize = 5;

    ArrayStack(){
        this.top = -1;
        this.store = new int[maxSize];
    }

    //判断是否为空
    public boolean isEmpty(){
        if(top==-1)
            return true;
        else return false;
    }

    //进栈
    public void push(int key){
        if(top==maxSize-1){                     //入栈前先判断栈是否已满
            System.out.println("栈已满");
            return;
        }else{
            store[++top] = key;
            System.out.println("成功入栈");
        }
    }
    //出栈
    public void pop(int[] res){
        if(top==-1){
            System.out.println("栈为空");
            return;
        }else{
            res[0] =  store[top--];
            return;
        }
    }

    //读取栈顶元素
    public void getTop(int[] res){
        if(top==-1){
            System.out.println("栈为空");
            return;
        }else{
            res[0] =  store[top];
            return;
        }
    }

    public static void main(String[] args) {
        int[] res = new int[1];
        int[] get = new int[1];
        ArrayStack stack = new ArrayStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.getTop(get);
        System.out.println(get[0]);
        stack.pop(res);
        System.out.println(res[0]);
        stack.pop(res);
        System.out.println(res[0]);
        System.out.println(stack.isEmpty());
    }

}
