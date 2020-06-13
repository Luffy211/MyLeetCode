package List.LinkList;

import java.util.HashMap;
import java.util.HashSet;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

public class LinkedList {
    /*
    21. 合并两个有序链表
    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

    输入：1->2->4, 1->3->4
    输出：1->1->2->3->4->4
*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //不带头结点的
        if(l1==null&&l2==null){
            return l1;
        }
        if(l1==null&&l2!=null){
            return l2;
        }
        if(l1!=null&&l2==null){
            return l1;
        }
        ListNode head = new ListNode();
        ListNode front;
        front = head;
        while(l1!=null&&l2!=null) {
            if (l1.val <=l2.val){
                front.next = l1;
                l1 = l1.next;
            }else{
                front.next = l2;
                l2 = l2.next;
            }
            front = front.next;
        }
        if(l1!=null)
            front.next = l1;
        if(l2!=null)
            front.next = l2;
        return head.next;
    }

        /*
    83. 删除排序链表中的重复元素
    给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 

    输入: 1->1->2
    输出: 1->2
*/
        //同不带头结点
        public ListNode deleteDuplicates(ListNode head) {
            if(head==null){
                return head;
            }
            ListNode front = head;
            ListNode current = head.next;
            while(current!=null){
                if(front.val==current.val){
                    front.next = current.next;          //前指针不动，后指针移动
                    current = current.next;
                }else{
                    front = front.next;                 //前后指针都移动
                    current = current.next;
                }
            }
            return head;
        }

                /*
    141. 环形链表
    给定一个链表，判断链表中是否有环。
为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
*/
    //此题进阶，求环的长度 找出入环点（利用hashSet找第一个contain的Node 即为入环点）
    public boolean hasCycle(ListNode head) {
        //方法1：哈希表法  HashSet也可以的
        /*
        HashMap<ListNode,Integer> map = new HashMap<>();
        if(head==null||head.next==null){
            return false;
        }
        while(head!=null){
            if(!map.containsKey(head)){
                map.put(head,1);
            }else{
                map.put(head,map.get(head)+1);
            }
            if(map.get(head)>=2){
                return true;
            }
            head = head.next;
        }
        return false;*/
        //方法2：快慢指针 慢指针P1速度1  快指针P2速度2  开始时都指向头结点 如果结束前又指向相同节点 则说明有环
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
/*
    160. 相交链表
编写一个程序，找到两个单链表相交的起始节点。

输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //法1：暴力法 O（n^2）
        //法2：因为相交之后的长度是相同的，所以将尾巴对齐，长的那个必须先移动long-short个指针，然后再同步比较
        if(headA==null||headB==null)
            return null;
        //求出 A B的长度
        int len1 = 0;
        int len2 = 0;
        ListNode a = headA;
        ListNode b = headB;
        while(a!=null){
            len1++;
            a = a.next;
        }
        while(b!=null){
            len2++;
            b = b.next;
        }
        int diff = Math.abs(len1-len2);
        if(len1>len2){
            ListNode c = headA;
            ListNode d = headB;
            for(int i=0;i<diff;i++){
                c = c.next;
            }
            while(c!=null&&d!=null){
                if(c==d)
                    return c;
                c=c.next;
                d=d.next;
            }
            return null;
        }else{
            ListNode c = headA;
            ListNode d = headB;
            for(int i=0;i<diff;i++){
                d = d.next;
            }
            while(c!=null&&d!=null){
                if(c==d)
                    return c;
                c=c.next;
                d=d.next;
            }
            return null;
        }
    }

    /*
    203. 移除链表元素
删除链表中等于给定值 val 的所有节点
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
*/
    //两种方法：1.双指针 2.单指针，赋值抹除
    public ListNode removeElements(ListNode head, int val) {
        if(head==null){
            return null;
        }
        ListNode newHead = new ListNode();          //新建一个节点作为头结点
        ListNode front = newHead;
        while(head!=null){
            if(head.val!=val){
                front.next = head;
                front = head;
            }else{
                front.next = head.next;
            }
            head = head.next;
        }
        return newHead.next;
    }

        /*
    206. 反转链表
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
*/
        public static void reverse(ListNode front,ListNode head){
            if(head==null)
                return;
            ListNode tmp = head.next;
            head.next = front.next;
            front.next = head;
            reverse(front,tmp);
        }
    public ListNode reverseList(ListNode head) {
        //头插法
        if(head==null){
            return head;
        }
        ListNode newHead = new ListNode();      //新建一个头指针
        ListNode front = newHead;
        /*while(head!=null){
            ListNode tmp = head.next;
            head.next = front.next;
            front.next = head;
            //front = head;                     无须移动头指针
            head = tmp;
        }
        return newHead.next;*/
        //递归法：
        reverse(front,head);
        return front.next;
        }

                /*
    234. 回文链表
    请判断一个链表是否为回文链表。
输入: 1->2
输出: false
输入: 1->2->2->1
输出: true
*/
    public boolean isPalindrome(ListNode head) {
        //法1:先计算链表的长度，奇数偶数做个判断；然后使用头插法逆转前半段，然后比较逆转后的前半段和后半段是否都一致O(n)
        if(head==null||head.next==null)
            return true;
        int len=0;
        ListNode tmp = head;
        while(tmp!=null){
            len++;
            tmp = tmp.next;
        }
        int half = len/2;
        ListNode firstHalf = new ListNode();
        for(int i=0;i<half;i++){
            ListNode tmp1 = head.next;
            head.next = firstHalf.next;
            firstHalf.next = head;
            head = tmp1;
        }
        if(len%2!=0){
            head = head.next;       //奇数时再下移一个
        }
        ListNode tmp2 = firstHalf.next;
        while(head!=null){
            if(tmp2.val!=head.val)
                return false;
            head = head.next;
            tmp2 = tmp2.next;
        }
        return true;
        //法2:头插法逆转整个链表得到新链表  然后从头到尾比较前后链表  需要O(n)空间
        //法3：使用数组保存链表元素值  也需要O(n)
    }

                    /*
    237. 删除链表中的节点
    请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
现有一个链表 -- head = [4,5,1,9]，它可以表示为:
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
说明:
链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。
*/
    public void deleteNode(ListNode head,ListNode node) {
        //方法1:双指针，找到之后使用前指针，指向当前指针节点的下一个
        if(head==null||head.next==null)         //长度小于2直接返回
            return;
        if(node==head)
            head = head.next;
        else {
            ListNode front = head;
            ListNode current = head.next;
            while (current!=node&&current!=null){
                front = front.next;
                current = current.next;
            }
            if(current!=null)           //说明找到了
                front.next = current.next;
        }
    }
    //方法2：单指针就够，找到之后，将下一节点的值赋予当前节点，当前节点指向下下节点。另一种删除方式
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

                        /*
    876. 链表的中间结点
给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
如果有两个中间结点，则返回第二个中间结点。
输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
*/
    public ListNode middleNode(ListNode head) {
        //方法1：先遍历出链表的长度 O(n)  再找中间O(n/2)
        if(head==null)
            return null;
        int len = 0;
        ListNode tmp = head;
        while(tmp!=null){
            len++;
            tmp = tmp.next;
        }
        int mid = len/2 + 1;
        ListNode tmp1 = head;
        for(int i=0;i<mid-1;i++){
            tmp1 = tmp1.next;
        }
        return tmp1;
    }

                            /*
1290. 二进制链表转整数
给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
请你返回该链表所表示数字的 十进制值 。
输入：head = [1,0,1]
输出：5
解释：二进制数 (101) 转化为十进制数 (5)
*/
    public int getDecimalValue(ListNode head) {
        //方法1：直接计算累加
        if(head==null)
            return 0;
        int sum = 0;
        ListNode tmp = head;
        while(tmp!=null){
            int val = tmp.val;
            sum = sum*2 + val;
            tmp = tmp.next;
        }
        return sum;
        //方法2：逆转链表 顺着来累加
    }

                                /*
面试题 02.01. 移除重复节点

编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
示例1:
 输入：[1, 2, 3, 3, 2, 1]
 输出：[1, 2, 3]
*/
    public ListNode removeDuplicateNodes(ListNode head) {
        //方法1：先排序
        //方法2:利用hash
        if(head==null)
            return null;
        HashSet<Integer> set = new HashSet<>();
        //单指针删除法--末尾节点不好处理:参考如下!!!
        /*
        if(head==null)
            return null;
        HashSet<Integer> set = new HashSet<>();
        ListNode tmp = head;
        set.add(head.val);
        while(tmp.next!=null) {                     //tmp不是末尾
            if (set.contains(tmp.next.val)) {
                    tmp.next = tmp.next.next;
            } else {
                set.add(tmp.next.val);
                tmp = tmp.next;
            }
        }
        return head;*/
        //双指针删除法
        ListNode front = head;
        ListNode tmp = head.next;
        set.add(head.val);
        while(tmp!=null){
            if(set.contains(tmp.val)){      //重复了，则front不动，tmp前移
                tmp = tmp.next;
                front.next = tmp;
            }else{                          //没出现过，则加入hashset  front和tmp一起前移
                set.add(tmp.val);
                front = tmp;
                tmp = tmp.next;
            }
        }
        return head;
        //冒泡排序的思想: 空间复杂度O(1)!!!
        /*
        ListNode p = head;
        while (p != null) {
            ListNode q = p;
            while (q.next != null) {
                if (q.next.val == p.val)
                    q.next = q.next.next;
                else
                    q = q.next;
            }
            p = p.next;
        }
        return head;*/
    }

                                    /*
面试题 02.02. 返回倒数第 k 个节点
实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
注意：本题相对原题稍作改动
示例：
输入： 1->2->3->4->5 和 k = 2
输出： 4
*/
    public int kthToLast(ListNode head, int k) {
        //方法1：先求出链表长len  然后倒数第k个就是正着数len-k+1个
        if(head==null)
            return 0;
        int len = 0;
        ListNode tmp = head;
        while(tmp!=null){
            len++;
            tmp = tmp.next;
        }
        int sign = len-k+1;
        ListNode tmp1 = head;
        for(int i=1;i<sign;i++){
            tmp1 = tmp1.next;
        }
        return tmp1.val;
        //方法2：利用头插法逆转链表 然后求逆转后的第k个。

}
}