package com.zoy.algorithm.linkedlist;

import lombok.Data;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/19
 */
@Data
public class ListNode {

    int val;
    ListNode next;

    public ListNode(int x){
        val=x;
    }
}
