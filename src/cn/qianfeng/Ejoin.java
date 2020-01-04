package cn.qianfeng;

/**
 * 加入线程
 * @author liuweijie
 *
 */
public class Ejoin {

	public static void main(String[] args) {
		MyJoin join = new MyJoin();
		Thread t1 = new Thread(join);
		t1.start();
		try {
			for (int i = 1; i <= 500; i++) {
				Thread.sleep(10);
				System.out.println("main:" + i);
				if(i==250) {
					t1.join();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyJoin implements Runnable{
	@Override
	public void run() {
		for (int i = 1; i <= 500; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MyJoin1---" + i);
		}
	}
}