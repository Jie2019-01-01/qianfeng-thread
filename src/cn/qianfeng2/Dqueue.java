package cn.qianfeng2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 队列的使用
 * @author liuweijie
 *
 */
public class Dqueue {

	public static void main(String[] args) {
//		final Queue<String> q = new LinkedList<String>();
		final Queue<String> q = new ConcurrentLinkedQueue<String>();
		
		class MyQueue implements Callable<String>{
			public String call() {
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				q.add("A");
				return null;
			}
		};
		
		long start = System.currentTimeMillis();
		
		MyQueue task = new MyQueue();
		ExecutorService es = Executors.newFixedThreadPool(3);
		for(int i=0; i<10; i++) {
			es.submit(task);
		}
		es.shutdown();
		
		while(true) {
			if(es.isTerminated()) {
				break;
			}
		}
		
		while(true) {
			if(q.peek()==null) {
				break;
			}
			System.out.print(q.poll() + "  ");
		}
		
		System.out.println();
		long end = System.currentTimeMillis();
		System.out.println("运行时长： " + (end-start) + "ms");
	}
}
