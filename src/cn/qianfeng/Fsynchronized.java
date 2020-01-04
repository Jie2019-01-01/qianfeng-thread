package cn.qianfeng;

/**
 * 线程同步,避免线程不安全情况
 * @author liuweijie
 *
 */
public class Fsynchronized {

	public static void main(String[] args) throws Exception{
		
		long start = System.currentTimeMillis();
		
		Bank bank = new Bank();
		Husband h = new Husband(bank);
		Wife w = new Wife(bank);
		Son s = new Son(bank);
		h.setName("Husband");
		w.setName("Wife");
		s.setName("Son");
		w.start();
		h.start();
		s.start();
		
		w.join();
		h.join();
		s.join();
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}

class Husband extends Thread{
	
	Bank bank;
	Husband(Bank bank){this.bank=bank;}
	
	public void run() {this.bank.getMonmey(600);};
}

class Wife extends Thread{
	
	Bank bank;
	Wife(Bank bank){this.bank=bank;}
	
	public void run() {this.bank.getMonmey(600);};
}

class Son extends Thread{
	
	Bank bank;
	Son(Bank bank){this.bank=bank;}
	
	public void run() {this.bank.getMonmey(600);};
}

class Bank{
	
	int sumMonmey = 1000;
	
	/**
	 * 取钱
	 */
	public void getMonmey(int monmey) {
		synchronized(this) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(sumMonmey < monmey) {
				System.out.println("余额不足....");
			}else {
				sumMonmey -= monmey;
				System.out.println(Thread.currentThread().getName() + "-取出"+ monmey +"元, 余额为:" + sumMonmey);
			}
		}
	}
}
