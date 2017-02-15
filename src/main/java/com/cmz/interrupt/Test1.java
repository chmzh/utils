package com.cmz.interrupt;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
	final static ReentrantLock putLock = new ReentrantLock();
	final static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	public static void main(String[] args) {
		Thread thread = new Thread(new Thread1());
		thread.start();
		thread.interrupt();
		try {
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
	}
	
	
	
	static class Thread1 implements Runnable{

		@Override
		public void run() {
			

				try {
					putLock.lockInterruptibly();
					queue.put("abc");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					//System.out.println(e.getMessage()+"aa");
				}finally {
					putLock.unlock();
				}
				
			} 
			

		
	}
	
}
