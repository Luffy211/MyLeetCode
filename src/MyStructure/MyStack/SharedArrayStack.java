package MyStructure.MyStack;

//共享栈  采用顺序存储  两个栈顶从两头开始top1 top2
//栈满的条件:top2-1 == top1

public class SharedArrayStack {
    private int maxSize=10;
    private int[] store;
    private int top1,top2;

    //初始化
    SharedArrayStack(){
        top1 = -1;
        top2 = maxSize;
        store = new int[maxSize];
    }

    //判断栈空
    public boolean isEmpty(){
        return top1==-1&&top2==maxSize;
    }

    //入栈
    public void push(int sign,int key){
        if(top2==top1+1){
            System.out.println("栈已满");
            return;
        }
        if(sign==1)                     //插入栈1
            store[++top1] = key;
        if(sign==2)                     //插入栈2
            store[--top2] = key;
        return;
    }

    //出栈
    public void pop(int sign,int[]res){
        if(sign==1){
            if(top1==-1) {
                System.out.println("栈空");
                return;
            }
            res[0] = store[top1--];
        }
        if(sign==2){
            if(top2==maxSize) {
                System.out.println("栈空");
                return;
            }
            res[0] = store[top2++];
        }
        return;
    }

    public void printStack(){
        if(top1==-1)
            System.out.println("第一个栈为空");
        else{
            System.out.println("第一个栈为： ");
            for(int i=0;i<=top1;i++){
                System.out.print(store[i]+" ");
            }
        }
        System.out.println();
        if(top1==maxSize)
            System.out.println("第二个栈为空");
        else{
            System.out.println("第二个栈为： ");
            for(int i=maxSize-1;i>=top2;i--){
                System.out.print(store[i]+" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SharedArrayStack stack = new SharedArrayStack();
        stack.push(1,3);
        stack.push(1,5);
        stack.push(1,7);
        stack.push(1,9);
        stack.push(2,2);
        stack.push(2,4);
        stack.push(2,6);
        stack.push(2,8);
        stack.printStack();
        int[] res = new int[1];
        stack.pop(1,res);
        stack.printStack();
    }
}
