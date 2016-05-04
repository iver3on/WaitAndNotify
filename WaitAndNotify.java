/**
 * 通过wait()来取代忙等待机制，当收到通知消息时，notify当前Monitor类线程。
 */
package net.zwb1;

import java.util.concurrent.TimeUnit;

/**
 * @author IVER3ON
 * @email grepzwb@qq.com
 * 2016年5月4日
 */
//被观察者
class IsObserved implements Runnable {
	// 传入观察者的实例
	Observe observe;

	public IsObserved(Observe observe) {
		this.observe = observe;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {	
			System.out.println("我要启动了。。");
			TimeUnit.SECONDS.sleep(2);
			observe.getMessage();
			System.out.println("我已经启动");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

//观察者
class Observe implements Runnable {

	//使用volatile关键字  修饰被不同线程访问和修改的变量   
	//被volatile修饰的变量每次使用的时候都是直接从相应的内存中提取，而不会利用缓存。使用volatile修饰成员变量后，所有线程在任何时候所看到
	//的变量的值都是相同的
	private volatile boolean start = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			watching();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public synchronized void  getMessage() throws InterruptedException{
		// TODO Auto-generated method stub
		// 被观察者以及启动
		start = true;
		notify();
	}

	public synchronized void watching() throws InterruptedException {
		while (start == false) {
			// ----------------
			wait();
			System.out.println("我是观察者，我在观察");
		}
		System.out.println("被观察者已经启动");
	}

}

public class WaitAndNotify {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Observe observe = new Observe();
		IsObserved isObserved = new IsObserved(observe);
		new Thread(isObserved).start();
		new Thread(observe).start();
	}

}
