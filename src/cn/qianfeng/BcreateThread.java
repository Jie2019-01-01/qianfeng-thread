package cn.qianfeng;

public class BcreateThread{
    public static void main(String[] args){
        MyThread t = new MyThread();
        t.start();
        MyThread2 t2 = new MyThread2();
        t2.start();
    }
}

class MyThread extends Thread{
    public void run(){
        for(int i=0; i<50; i++) {
        	System.out.println("MyThread--" + i);
        }
    }
}

class MyThread2 extends Thread{
    public void run(){
        for(int i=0; i<50; i++) {
        	System.out.println("MyThread2--" + i);
        }
    }
}
