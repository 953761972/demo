package com.example.demo.算法.leetcode;

import java.util.Stack;

public class 两数相加_002 {

    public static void main(String[] args) {
        ListNode l1=new ListNode();
        l1.val=2;
        ListNode l2=new ListNode();
        l2.val=4;
        l1.next=l2;
        ListNode l3=new ListNode();
        l3.val=3;
        l2.next=l3;


        ListNode l4=new ListNode();
        l4.val=5;
        ListNode l5=new ListNode();
        l5.val=6;
        l4.next=l5;
        ListNode l6=new ListNode();
        l6.val=4;
        l5.next=l6;
        ListNode result=addTwoNumbers(l1,l4);
        while(result !=null){
            System.out.println(result.val);
            result=result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode l=new ListNode();
        ListNode lhead=l;
        while(l1!=null || l2!=null){
            ListNode newNode=new ListNode();
            int result=0;
            if(l1!=null){
                result += l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                result += l2.val;
                l2=l2.next;
            }
            result+=carry;
            carry=result/10;
            newNode.val=result%10;
            lhead.next=newNode;
            lhead=newNode;
        }
        if(carry!=0){
            ListNode ll=new ListNode();
            ll.val=carry;
            lhead.next=ll;
        }
        return l.next;
    }
}

   class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }