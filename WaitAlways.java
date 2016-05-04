/**
 * ͨ��æ�ȴ������ϵļ���־λ�Ƿ�仯��
 */
package net.zwb;

import java.util.concurrent.TimeUnit;

import net.zwb.IsObserved;
import net.zwb.Observe;

/**
 * @author IVER3ON
 * @email grepzwb@qq.com 2016��5��4��
 */

// ���۲���
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

// �۲���
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
		watching();
	}

	/**
	 * 
	 */
	public void getMessage() {
		// TODO Auto-generated method stub
		// ���۲����Լ�����
		start = true;
	}

	public void watching() {
		while (start == false) {
			// ----------------
			System.out.println("���ǹ۲��ߣ����ڹ۲�");
		}
		System.out.println("���۲����Ѿ�����");
	}

}

public class WaitAlways {
	public static void main(String[] args) {
		Observe observe = new Observe();
		IsObserved isObserved = new IsObserved(observe);
		new Thread(isObserved).start();
		new Thread(observe).start();
	}
}
