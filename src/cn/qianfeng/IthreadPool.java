package cn.qianfeng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池使用
 * @author liuweijie
 */
public class IthreadPool {

	public static void main(String[] args) {
		
//		long start = System.currentTimeMillis();
		// 创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		MyPool mp = new MyPool();
		pool.submit(mp);
		pool.submit(mp);
		pool.submit(mp);
		pool.submit(mp);
		
		pool.shutdown();
		
		long end = System.currentTimeMillis();
//		System.out.println("运行时间：" + (end-start) + "ms");
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
