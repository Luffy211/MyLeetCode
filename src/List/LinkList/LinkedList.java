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


}