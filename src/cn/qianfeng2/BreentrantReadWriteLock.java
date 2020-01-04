package cn.qianfeng2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class BreentrantReadWriteLock {
	public static void main(String[] args) {
		final Student s = new Student();
		Callable<String> writeTask = new Callable<String>() {
			public String call() throws Exception{
				s.setValue("aaa");
				return null;
			}
		};
		
		Callable<String> readTask = new Callable<String>() {
			public String call() throws Exception{
				s.getValue();
				return null;
			}
		};
		
		long start = System.currentTimeMillis();
		ExecutorService es = Executors.newFixedThreadPool(20);
		// 写线程
		for (int i = 0; i < 2; i++) {
			es.submit(writeTask);
		}
		// 读线程
		for (int i = 0; i < 18; i++) {
			es.submit(readTask);
		}
		
		es.shutdown();
		// 计时
		while(true) {
			// 线程池中的任务执行完毕后会返回true
			if(es.isTerminated()) {
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("用时：" + (end-start) + "ms");
	}
}

class Student{
	
//	ReentrantLock rl = new ReentrantLock();
	ReentrantReadWriteLock rrl = new ReentrantReadWriteLock();
	ReadLock rl = rrl.readLock(); // 获取读锁
	WriteLock wl = rrl.writeLock(); // 获取写锁
	
	private String value;
	
	// 读操作
	public String getValue() {
		rl.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "-读操作,,,,,,,");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			rl.unlock();
		}
		return this.value;
	}
	// 写操作
	public void setValue(String value) {
		wl.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "-写操作.....");
			Thread.sleep(1000);
			this.value=value;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			wl.unlock();
		}
	}
}
