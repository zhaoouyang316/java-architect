package com.zoy.algorithm.linkedlist;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/19
 */
public class ReverseListNode {

    /**
     * 反转链表
     */
    public void testReverseListNode(){

        ListNode listNode1=new ListNode(1);
        ListNode listNode2=new ListNode(2);
        ListNode listNode3=new ListNode(3);
        ListNode listNode4=new ListNode(4);
        ListNode listNode5=new ListNode(5);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);
        listNode4.setNext(listNode5);

        ListNode curr=listNode1;
        ListNode prev=null;

        while(curr!=null){
            ListNode nextTemp=curr.next;
        }
    }

}
