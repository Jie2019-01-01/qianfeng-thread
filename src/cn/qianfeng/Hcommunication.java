package cn.qianfeng;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程之间的通信，生产者与消费者对应多条线程类，属性都有Queue作为中间媒介，实现线程案例的操作。
 * @author liuweijie
 *
 */
public class Hcommunication {

	public static void main(String[] args) {
		
		MyQueue q = new MyQueue();
		Produce1 p1 = new Produce1(q);
		p1.start();
		
		Produce2 p2 = new Produce2(q);
		p2.start();
		
		Consumer c = new Consumer(q);
		c.start();
				
//		queue.show();
	}
}

class MyQueue{
	
	int count = 1; //计数，表示存入的第几个元素
	int max = 4; //队列中允许存入的最大数量
	
	List<Object> queue = new ArrayList<Object>();
	
	/**
	 * 存入
	 */
	synchronized public void offer(Object obj) {
		
		// 如果队列已满，则不可以继续存入，等待穴位
		while(queue.size()==max) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		queue.add(obj);
		System.out.println(Thread.currentThread().getName() + "-offer: 第" + count +"个元素'"+ obj +"'已经存入。");
		count++;
		this.notifyAll();
	}
	
	/**
	 * 取出
	 */
	public void poll() {
		synchronized(this) {
			while(queue.size()==0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("移除了" + queue.remove(0)); // 遵循先入先出的原则
			this.notifyAll();
		}
	}
	
	public void show() {
		System.out.println("剩余的元素-----");
		for (Object obj : queue) {
			System.out.println("\t" + obj);
		}
	}
}

class Produce1 extends Thread{
	
	private MyQueue queue;
	Produce1(MyQueue queue){this.queue=queue;}
	
	@Override
	public void run() {
		queue.offer("A");
		queue.offer("B");
		queue.offer("C");
		queue.offer("D");
	}
}

class Produce2 extends Thread{
	
	private MyQueue queue;
	Produce2(MyQueue queue){this.queue=queue;}
	
	@Override
	public void run() {
		queue.offer("X");
		queue.offer("Y");
		queue.offer("Z");
	}
}

class Consumer extends Thread{
	
	private MyQueue queue;
	Consumer(MyQueue queue){this.queue=queue;}
	
	@Override
	public void run() {
		for (int i = 1; i <= 7; i++) {
			queue.poll();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
