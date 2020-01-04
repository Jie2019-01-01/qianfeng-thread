package cn.qianfeng2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建线程方式三：Callable接口
 * @author liuweijie
 */
public class Acallable {

	public static void main(String[] args) throws Exception{
		
		long start = System.currentTimeMillis();
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		MyCallable1 task1 = new MyCallable1();
		Future<Integer> f1 = pool.submit(task1);
		
		MyCallable2 task2 = new MyCallable2();
		Future<Integer> f2 = pool.submit(task2);
		
		Integer result1 = f1.get(); // 此时进入无限期等待
		Integer result2 = f2.get(); // 此时进入无限期等待
		
		System.out.println("result1===" + result1);
		System.out.println("result2===" + result2);
		
		System.out.println("两次线程累积的返回值是：" + (result1 + result2));
		
		pool.shutdown();
		
		long end = System.currentTimeMillis();
		System.out.println("程序执行时长：" + (end-start));
	}
}

class MyCallable1 implements Callable<Integer>{
	
	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		int sum = 0;
		for (int i = 1; i <=50; i++) {
			sum += i;
			System.out.println(Thread.currentThread().getName() +": " + i);
		}
		return sum;
	}
}

class MyCallable2 implements Callable<Integer>{
	
	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		int sum = 0;
		for (int i = 51; i <=100; i++) {
			sum += i;
			System.out.println(Thread.currentThread().getName() +": " + i);
		}
		return sum;
	}
}
