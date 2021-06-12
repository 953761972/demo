package com.example.demo.算法;

import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

import java.util.Stack;

/**
 * @Author XZQ
 * @Date 2021/5/29 22:19
 **/
public class 树 {
    public static void main(String[] args) {
        TreeNode t=new TreeNode(0);
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(3);
        TreeNode t4=new TreeNode(4);
        TreeNode t5=new TreeNode(5);
        t3.left=t2;
        t2.left=t1;
        t1.left=t;
        t3.right=t4;
        t4.right=t5;

        inOrderTraversal(t3);

    }

    //中序遍历
    public static void inOrderTraversal(TreeNode t){

        Stack<TreeNode> stack=new Stack<>();
        while ( t!=null || !stack.isEmpty()){
            while(t!=null){
                stack.push(t);
                t=t.left;
            }
            if(!stack.isEmpty()){
                t=stack.pop();
                System.out.println(t.val);
                t=t.right;
            }
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
