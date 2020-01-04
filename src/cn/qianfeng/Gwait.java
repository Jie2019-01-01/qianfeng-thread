package cn.qianfeng;

/**
 * wait与notify方法
 * @author liuweijie
 *
 */
public class Gwait {

	public static void main(String[] args) throws InterruptedException {
		Object o = new Object();
		TestThread t1 = new TestThread(o);
		t1.start();
		TestThread2 t2 = new TestThread2(o);
		t2.start();
		Thread.sleep(3000);
		synchronized(o) {
			System.out.println("main进入同步代码块");
//			o.notify(); // 随机唤醒一个因o进入等待状态的线程
			o.notifyAll(); // 唤醒全部因o进入等待状态的线程
			System.out.println("main退出同步代码块");
		}
	}
}

class TestThread extends Thread{
	
	Object obj;
	
	TestThread(Object obj){this.obj=obj;}
	
	@Override
	public void run() {
		synchronized (obj) {
			System.out.println("t1进入同步代码块");
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t1同步代码块执行结束");
		}
		System.out.println("t1线程执行完毕");
	}
}

class TestThread2 extends Thread{
	
	Object obj;
	
	TestThread2(Object obj){this.obj=obj;}
	
	@Override
	public void run() {
		synchronized (obj) {
			System.out.println("t2进入同步代码块");
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2同步代码块执行结束");
		}
		System.out.println("t2线程执行完毕");
	}
}
