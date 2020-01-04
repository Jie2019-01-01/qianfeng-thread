package cn.qianfeng;

/**
 * 休眠线程
 * @author liuweijie
 *
 */
public class Csleep {

	public static void main(String[] args) {
		Thread t1 = new Thread(new MySleep());
		Thread t2 = new Thread(new MySleep2());
		t1.start();
		t2.start();
	}
}

class MySleep implements Runnable{
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println("MySleep1---" + i);
			if(i==25) {
				try {
					System.out.println("\t\tMySleep1休眠2秒");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class MySleep2 implements Runnable{
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println("MySleep2====" + i);
		}
	}
}
