package cn.qianfeng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池使用
 * @author liuweijie
 */
public class IthreadPool {

	public static void main(String[] args) {
		// 创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		MyPool mp = new MyPool();
		pool.submit(mp);
		pool.submit(mp);
		pool.submit(mp);
		pool.submit(mp);
		
		pool.shutdown();
	}
}

class MyPool implements Runnable{
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(Thread.currentThread().getName() + " =======" + i);
		}
	}
}
