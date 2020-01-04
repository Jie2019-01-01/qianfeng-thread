package cn.qianfeng;

/**
 * 礼让线程,回到就绪状态,只让一次
 * @author liuweijie
 *
 */
public class Dyield {

	public static void main(String[] args) {
		MyYield t1 = new MyYield();
		MyYield2 t2 = new MyYield2();
		t1.start();
		t2.start();
	}
}

class MyYield extends Thread{
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println("MyYield1---" + i);
			if(i==25) {
				System.out.println("\t\tMyYield1让出CPU,等待MyYield2执行完毕");
				Thread.yield();
			}
		}
	}
}

class MyYield2 extends Thread{
	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			System.out.println("MyYield2=====" + i);
		}
	}
}
