package cn.qianfeng;

/**
 * 实现Runnable
 * @author liuweijie
 *
 */
public class AcreateRunnable{

	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunnable());
		Thread t2 = new Thread(new MyRunnable2());
		t1.start();
		t2.start();
	}
}

class MyRunnable implements Runnable{
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println("MyRunnable1---" + i);
		}
	}
}

class MyRunnable2 implements Runnable{
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println("MyRunnable2===" + i);
		}
	}
}
