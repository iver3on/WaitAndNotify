/**
 * TestLockAndSynchronized  是两种不同的多线程同步机制 所以在同步中不要同时使用Synchronized和Lock
 */
package net.zwb1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author IVER3ON
 * @email grepzwb@qq.com
 * 2016年5月4日
 */
public class TestLockAndSynchronized {

	class SyncTest{
		private int value = 0;
		Lock lock = new ReentrantLock();
		
		public synchronized void addValueSync(){
			this.value++;
			System.out.println(Thread.currentThread().getName()+":"+value);
		}
		
		public void addValueLock(){
			try{
			lock.lock();
			value++;
			System.out.println(Thread.currentThread().getName()+":"+value);
			}finally{
				lock.unlock();
			}
		}
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestLockAndSynchronized tla = new TestLockAndSynchronized();
		final SyncTest st = tla.new SyncTest();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<5;i++){
					st.addValueSync();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<5;i++){
					st.addValueSync();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
		t2.start();
	}

}
