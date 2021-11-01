package com.example.demo.公司面试;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author XZQ
 * @Date 2021/4/8 12:55
 **/
public class myxd {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入完成");
        Set<Integer> ts = new TreeSet();
        while (sc.hasNext()) {
            int size= sc.nextInt();
            for(int i=0;i<size;i++){
                ts.add(sc.nextInt());
            }
        }

        ts.forEach(System.out::println);
    }

    //正则表达式测试
    @Test
    public void testparrten(){
        //匹配邮箱
        String str="953761972@qq.com";
        System.out.println(str.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$"));
        //匹配http地址
        String http="http://localhost:8080/hcbmp";
        Pattern t=Pattern.compile("(http:\\/\\/)([a-zA-Z0-9]+):([0-9]{1,5})([a-zA-Z0-9/_#]+)");
        Matcher m= t.matcher(http);
        Pattern.matches("","");
        System.out.println(m.groupCount());
        m.find();//必须先调用他
        System.out.println("start:"+m.start());
        System.out.println("end:"+m.end());

        for(int i=0;i<m.groupCount()+1;i++){
            System.out.println(i+" :"+m.group(i));
        }
    }

    @Test
    public void testRandom(){
        //第一种
        Random r = new Random(1);//随机数种子，实际上种子一样，随机数就一样
        System.out.println(r.nextInt(100));//限制范围

        //第二种
        System.out.println( (int)(Math.random()*100) );//范围为0-100

        int min=90;//最小值
        int max=100;//最大值
        System.out.println( (int)(Math.random()*(max-min)+min ) );//范围为90-100

        //第三种
        System.out.println(System.currentTimeMillis()%(max-min)+min);//范围同上
    }


   @Test//获取100万个随机数
   public  void testRandom1to100w() {
        boolean[] bt=new boolean[1000000];
        int[] result=new int[1000000];
            for(int i=0;i<bt.length;){
                int num=(int)Math.random()*(1000000-1)+1;
                if(bt[num]==false) {
                    bt[num] = true;
                    i++;
                    result[i]=num;
                }
            }
            System.out.println(result.length);
        }

    @Test
    public  void testGetRemoteHtml() throws IOException {
            InputStream inputStream = null;
            try {
            URL url = new URL("http://www.baidu.com");
            URLConnection urlConnection = url.openConnection();
            inputStream= urlConnection.getInputStream();
            byte[] b = new byte[4096];
            while ((inputStream.read(b)) != -1) {
                System.out.println(new String(b));
            }
        }catch (Exception e){

        }finally {
                inputStream.close();
            }
    }

    @Test
    public  void testGetRemoteHtml1() throws IOException {
        InputStream inputStream = null;
        BufferedReader bfr=null;
        int i = 0;
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection urlConnection = url.openConnection();
            inputStream= urlConnection.getInputStream();
            InputStreamReader isr=new InputStreamReader(inputStream,"utf-8");
            bfr=new BufferedReader(isr);
            String line;
            while ((line=bfr.readLine())!=null) {
                System.out.println(line);
                System.out.println(i++);
            }
        }catch (Exception e){

        }finally {
            inputStream.close();
        }
    }

    @Test
    public  void TraverseFiles() throws IOException {
     File f=new File("/Volumes/FastSSD/files");
        listdir(f,1);
    }
    public void listdir(File f,int dirnum){
            if(f==null||!f.exists()){
                return;
            }
            if(!f.isDirectory()){
                System.out.println(dirnum+" "+f.getName());
                return;
            }
            System.out.println(f.getName());
            File f1[]=f.listFiles();
            for(int i=0;i<f1.length;i++){
                System.out.println(dirnum+" "+f1[i].getName());
                if(f1[i].isDirectory()){
                    dirnum+=1;
                    listdir(f1[i],dirnum);
                }
            }
    }
}
