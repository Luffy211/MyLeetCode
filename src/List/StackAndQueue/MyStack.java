package List.StackAndQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyStack {

    public static boolean isValid(String s) {
        char[] str = s.toCharArray();
        if(str.length<=1)
            return false;
        Stack stack = new Stack();
        for(char x : str){
            if(x=='('||x=='['||x=='{'){
                stack.push(x);
                continue;
            }
            char t = 'a';
            if(!stack.empty()&&stack.peek() instanceof Character)
                    t = (Character) stack.pop();
            else
                return false;

            if((x==')'&&t=='(')||(x==']'&&t=='[')||(x=='}'&&t=='{')) {
                continue;
            }
            else
                return false;
        }
        if(stack.empty())
            return true;
        else
            return false;
    }

    class MinStack {
        List<Integer> array;
        int topIndex;
        int minVal;
        /** initialize your data structure here. */
        public MinStack() {
            array = new ArrayList<>();
            this.topIndex = -1;
            this.minVal = Integer.MAX_VALUE;
        }

        public void push(int x) {
            array.add(++topIndex,x);
            if(topIndex==0)
                this.minVal = x;
            else {
                if(x<this.minVal)
                    this.minVal = x;
            }
        }

        public void pop() {
            if(array.get(topIndex)!=minVal)
                topIndex--;
            else{
                topIndex--;
                if(topIndex>=0){
                    int min = array.get(0);
                    for(int i=1;i<=topIndex;i++){
                        if(array.get(i)<min)
                            min = array.get(i);
                    }
                    this.minVal = min;
                }
            }
        }

        public int top() {
            System.out.println(topIndex);
            return array.get(topIndex);
        }

        public int getMin() {
            return this.minVal;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    public static void main(String[] args) {
        String s = "()";
        //System.out.println(isValid(s));
        List<Integer> list = new ArrayList<>();
        list.add(0,2);
        list.add(1,3);
        System.out.println(list.get(0));

    }
}
