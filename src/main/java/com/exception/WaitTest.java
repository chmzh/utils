package com.exception;

public class WaitTest {

	private final static Object object = new Object();
	private final static Object object1 = new Object();
	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();

		Thread2 thread2 = new Thread2();

		thread1.start();
		//thread2.start();
		//thread1.interrupt();
	}

	static class Thread1 extends Thread {
		@Override
		public void run() {
			try {
				synchronized (object) {
					object.wait();					
					System.out.println("continue");
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class Thread2 extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				object.notify();
			}

		}
	}
}
