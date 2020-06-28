package List;

import java.util.HashMap;
import java.util.Stack;

public class MyString {
    /*
    13. 罗马数字转整数
    罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     */
    public static int getValue(char c){
        switch (c) {
            case 'I':   return 1;
            case 'V':   return 5;
            case 'X':   return 10;
            case 'L':   return 50;
            case 'C':   return 100;
            case 'D':   return 500;
            case 'M':   return 1000;
            default:    return 0;
        }
    }
    public int romanToInt(String s) {
        //解法1：利用hashmap，速度较慢
        /*
        if(s.equals("")||s==null)
            return 0;
        HashMap<Character,Integer>valueMap = new HashMap<>();
        valueMap.put('I',1);
        valueMap.put('V',5);
        valueMap.put('X',10);
        valueMap.put('L',50);
        valueMap.put('C',100);
        valueMap.put('D',500);
        valueMap.put('M',1000);
        HashMap<Character,Integer>orderMap = new HashMap<>();
        orderMap.put('I',1);
        orderMap.put('V',2);
        orderMap.put('X',3);
        orderMap.put('L',4);
        orderMap.put('C',5);
        orderMap.put('D',6);
        orderMap.put('M',7);
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length;i++){
            stack.push(str[i]);
        }
        int res = 0;
        while(!stack.empty()){
            char c = stack.pop();
            if(!stack.empty()&&orderMap.get(stack.peek())<orderMap.get(c)&&(stack.peek()=='I'||stack.peek()=='X'||stack.peek()=='C')){
                char t = stack.pop();
                res = res + valueMap.get(c) - valueMap.get(t);
            }else{
                res += valueMap.get(c);
            }
        }
        return res;*/
        //解法2:直接从左到右遍历，当前比下一位小，则当前要用来减。
        //使用switch返回value，比hashmap快很多
        if(s.equals("")||s==null)
            return 0;
    }
}
