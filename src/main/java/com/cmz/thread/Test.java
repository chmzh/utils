package com.cmz.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import com.google.common.util.concurrent.UncheckedExecutionException;

public class Test {
	public static void main(String[] args) {
//		t1();
		testTry();
	}
	
	public static void testTry(){
		Person person = new Person();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				person.setMoney(1);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				person.getMoney();
			}
		}).start();
	}
	
	static class Person{
		
		private Lock lock = new ReentrantLock();
		
		public void setMoney(int money){
			try {
				lock.lock();
				while (true) {
					
				}
			} finally {
				lock.unlock();
			}
		}
		
		public int getMoney(){
			int money = 0;
			boolean bol = false;
			try {
				bol = lock.tryLock(1000, TimeUnit.MILLISECONDS);
				money = 1;
				System.out.println(bol+""+money);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(bol)
					lock.unlock();
			}
			return money;
		}
	}
	
	
	
	private static void t1(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					try {
						wait(1000);
						System.out.println("t1");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		});
		thread.start();
	}
	
	private static void t(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				throw new RuntimeException();
				
			}
		});
		
		thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				//e.printStackTrace();
				//System.out.println("aa");
			}
		});
		thread.start();
	}
}
