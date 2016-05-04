/**
 * ͨ��wait()��ȡ��æ�ȴ����ƣ����յ�֪ͨ��Ϣʱ��notify��ǰMonitor���̡߳�
 */
package net.zwb1;

import java.util.concurrent.TimeUnit;

/**
 * @author IVER3ON
 * @email grepzwb@qq.com
 * 2016��5��4��
 */
//���۲���
class IsObserved implements Runnable {
	// ����۲��ߵ�ʵ��
	Observe observe;

	public IsObserved(Observe observe) {
		this.observe = observe;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {	
			System.out.println("��Ҫ�����ˡ���");
			TimeUnit.SECONDS.sleep(2);
			observe.getMessage();
			System.out.println("���Ѿ�����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

//�۲���
class Observe implements Runnable {

	//ʹ��volatile�ؼ���  ���α���ͬ�̷߳��ʺ��޸ĵı���   
	//��volatile���εı���ÿ��ʹ�õ�ʱ����ֱ�Ӵ���Ӧ���ڴ�����ȡ�����������û��档ʹ��volatile���γ�Ա�����������߳����κ�ʱ��������
	//�ı�����ֵ������ͬ��
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
		// ���۲����Լ�����
		start = true;
		notify();
	}

	public synchronized void watching() throws InterruptedException {
		while (start == false) {
			// ----------------
			wait();
			System.out.println("���ǹ۲��ߣ����ڹ۲�");
		}
		System.out.println("���۲����Ѿ�����");
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
