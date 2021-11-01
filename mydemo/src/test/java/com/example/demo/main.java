package com.example.demo;

import java.util.*;

/**
 * @Author XZQ
 * @Date 2021/5/2 22:11
 **/
public class main {
    public static void main(String[] args) {
        char board[][] =
{{'5','3','.','.','7','.','.','.','.'}
,{'6','.','.','1','9','5','.','.','.'}
,{'.','9','8','.','.','.','.','6','.'}
,{'8','.','.','.','6','.','.','.','3'}
,{'4','.','.','8','.','3','.','.','1'}
,{'7','.','.','.','2','.','.','.','6'}
,{'.','6','.','.','.','.','2','8','.'}
,{'.','.','.','4','1','9','.','.','5'}
,{'.','.','.','.','8','.','.','7','9'}};

        ListNode node=new ListNode(1);
        node.next=new ListNode(2);
        node.next.next=new ListNode(2);
        node.next.next.next=new ListNode(1);
//        System.out.println(isPalindrome(node));
//        HashMap<Integer,Integer> map=new HashMap();
//        for(Map.Entry<Integer,Integer> e:map.entrySet()){
//            e.getValue();
//        }
        String str="dd";
        System.out.println(str.charAt(0));
        Set set=new HashSet();
        set.contains("");
        Deque<Object> deque = new LinkedList<>();
        deque.push("1");
        deque.addFirst("2");
        deque.pop();
        deque.addLast("2");
        System.out.println( Arrays.toString(deque.toArray()) );
    }

    public static boolean isValidSudoku(char board[][]) {
        int length = board.length;
        //二维数组line表示的是对应的行中是否有对应的数字，比如line[0][3]
        //表示的是第0行（实际上是第1行，因为数组的下标是从0开始的）是否有数字3
        int line[][] = new int[length][length];
        int column[][] = new int[length][length];
        int cell[][] = new int[length][length];
        for (int i = 0; i < length; ++i)
            for (int j = 0; j < length; ++j) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                //num是当前格子的数字
                int num = board[i][j] - '0' - 1;
                //k是第几个单元格，9宫格数独横着和竖着都是3个单元格
                int k = i / 3 * 3 + j / 3;
                //如果当前数字对应的行和列以及单元格，只要一个由数字，说明冲突了，直接返回false。
                //举个例子，如果line[i][num]不等于0，说明第i（i从0开始）行有num这个数字。
                if (line[i][num] != 0 || column[j][num] != 0 || cell[k][num] != 0)
                    return false;
                //表示第i行有num这个数字，第j列有num这个数字，对应的单元格内也有num这个数字
                line[i][num] = column[j][num] = cell[k][num] = 1;
            }
        return true;
    }

    public boolean isValidSudoku1(char[][] board) {
        int length=board.length;
        int column[]=new int[length];
        int line[]=new int[length];
        int cell[]=new int[length];

        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                int num= 1 << (board[i][j] - '0');
                int k=i/3*3+j/3;
                if((column[i] & num)>0||(line[j] & num)>0||(cell[k] & num)>0){
                    return false;
                }
                column[i] |=num;
                line[j] |=num;
                cell[k] |=num;

            }
        }
        return true;
    }
    public static boolean isPalindrome(String s) {
        s=s.replaceAll("[^a-zA-Z]","");
        s=s.toLowerCase();
        System.out.println(s);
        char[] ss=s.toCharArray();
        int left=0;
        int right=ss.length-1;

        while(left<right){
            if(ss[left++]!=ss[right--]){
                return false;
            }
        }
        return true;
    }

    public static int myAtoi(String s) {
        char[] ss=s.toCharArray();
        int i=0;
        int result=0;
        //是否负数
        boolean flag=false;
        if(ss.length==0){
            return 0;
        }
        while(i<ss.length && ss[i]==' '){
            i++;
        }
        if(i<ss.length && ss[i]=='-'){
            flag=true;
            i++;
        }
        if(i<ss.length && ss[i]=='+' && flag==false){
            i++;
        }


        while( i< ss.length ){
            if((ss[i]>='0'&&ss[i]<='9')){
                int number=ss[i]-'0';
                if(flag){
                    int temp=result*10 - number;
                    if((temp+number)/10==result){
                        result=temp;
                    }else{
                        return Integer.MIN_VALUE;
                    }
                }else{
                    int temp=result*10 + number;
                    if((temp - number)/10==result || temp<0){
                        result=temp;
                    }else{
                        return Integer.MAX_VALUE;
                    }
                }
                i++;
            }else{
                break;
            }
        }
        return result;
    }
    public static int strStr(String haystack, String needle) {
        int nlength=needle.length();
        int hlength=haystack.length();
        if(nlength==0){
            return 0;
        }
        if(nlength>hlength){
            return -1;
        }

        for(int i=0;i<hlength;i++){
            int left =i;
            for(int j=0;j<nlength;j++){
                if(left>hlength-1){
                    break;
                }
                if(haystack.charAt(left)!=needle.charAt(j)){
                    break;
                }
                left++;
            }
            if((left-i)==nlength) {return i;}
        }
        return -1;
    }

    public static String countAndSay(int n) {
        if(n==1) return "1";
        String current=countAndSay(n-1);
        char[] currentArr=current.toCharArray();

        int currIndex=0;
        int rightIndex=currIndex+1;
        int count=1;
        String result="";
        while(currIndex<currentArr.length) {
            if(rightIndex<currentArr.length&&currentArr[currIndex]==currentArr[rightIndex]){
                rightIndex++;
                count++;
            }else{
                if(rightIndex==currentArr.length){
                    result+=count;
                    result+=currentArr[currIndex];
                    return result;
                }
                result+=count;
                result+=currentArr[currIndex];
                currIndex=rightIndex;
                rightIndex=currIndex+1;
                count=1;
            }
        }
        return result;
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[1];
        }

        String str0=strs[0];
        String result="";
        char char0[]=str0.toCharArray();
        for(int i=0;i<char0.length;i++){
            int index=1;
            //比较的索引小于字符串数组的长度&&比较的字符索引小于当前位置的字符串长度&&比较的位置两个字符相等
            while(index<strs.length&&i<strs[index].length()&&char0[i]==strs[index].charAt(i)){
                index++;
            }
            if(index==strs.length){
                result+=char0[i];
            }else{
                break;
            }
        }
        return result;
    }

    public static String longestCommonPrefix1(String[] strs) {
        if(strs==null||strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[0];
        }

        String pre=strs[0];
        int index=1;
        while(index<strs.length){
            while(strs[index].indexOf(pre)!=0){
                pre=pre.substring(0,pre.length()-1);
            }
            index++;
        }
        return pre;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode left=head;
        ListNode right=head;
        while(right!=null && right.next!=null){
            right=right.next.next;
            left=left.next;
        }
        if(right !=null){
            left=left.next;
        }
        //反转链表
        ListNode left1=null;
        ListNode rhead=left;
        while (left!=null){

            rhead=rhead.next;
            left.next=left1;
            left1=left;
            left=rhead;

        }

        while(left1!=null&& head!=null){
            if(head.val!=left1.val){
                return false;
            }
            head=head.next;
            left1=left1.next;
        }
        return true;
    }

    public int maxDepth(TreeNode root) {

        if(root == null){
            return 0;
        }
        Stack<TreeNode> stack=new Stack();
        Stack<Integer> level=new Stack();

        int max=0;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            int temp=level.pop();
            max= (max>temp)?max:temp;
            if(node.left != null){
                stack.push(node.left);
                level.push(temp+1);
            }
            if(node.right != null){
                stack.push(node.right);
                level.push(temp+1);
            }
        }
        return max;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }
    public boolean isValidBST(TreeNode root,Integer min,Integer max){
        if(root == null){return true;}
        if(root.val>=max || root.val<=min){return false;}
        return isValidBST(root.left,min,root.val) && isValidBST(root.right,root.val,max);
    }
}


   class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 class TreeNode {
      int val;
     TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
         this.left = left;
          this.right = right;
      }
  }