package cn.qianfeng2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BreentrantLock {
	public static void main(String[] args) {
		BankCard bc = new BankCard();
		
		Husband h = new Husband(bc);
		Wife w = new Wife(bc);
		
		h.setName("Husband");
		w.setName("Wife");
		
		h.start();
		w.start();
	}
}

class BankCard{
	
	Lock lock = new ReentrantLock();// 定义锁
	
	int balance = 1000; //卡内余额
	/**
	 * 取款
	 */
	public void take(int money) {
		lock.lock();
		try {
			if(balance > money) {
				// 睡眠0.5秒
				try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
				balance -= money;
				System.out.println(Thread.currentThread().getName() + "-取走了" + money +", 卡内剩余: " + balance);
			}else {
				System.out.println("卡内余额不足...");
			}
		}finally {
			lock.unlock();
		}
	}
}

class Husband extends Thread{
	private BankCard bankCard;
	public Husband(BankCard bankCard) {this.bankCard=bankCard;}
	public void run() {
		bankCard.take(800);
	}
}

class Wife extends Thread{
	private BankCard bankCard;
	public Wife(BankCard bankCard) {this.bankCard=bankCard;}
	public void run() {
		bankCard.take(600);
	}
}
