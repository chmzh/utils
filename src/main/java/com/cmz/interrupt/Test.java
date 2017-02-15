package com.cmz.interrupt;

public class Test {
	public static void main(String[] args) {
		Thread thread = new Thread(new Thread1());
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
	
}
class Thread1 implements Runnable{

	@Override
	public void run() {
		try{
				while(!Thread.currentThread().isInterrupted()){
				try {
					
						System.out.println("work");
						Thread.sleep(10000);
					}
					
				 catch (InterruptedException e) {
					System.out.println(Thread.currentThread().isInterrupted());
					Thread.currentThread().interrupt();
					System.out.println(Thread.currentThread().isInterrupted());
				}
			}
		}finally{
			System.out.println(1);
		}
	}
	
}
