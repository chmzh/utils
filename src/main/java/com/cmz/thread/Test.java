package com.cmz.thread;

import java.lang.Thread.UncaughtExceptionHandler;

import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import com.google.common.util.concurrent.UncheckedExecutionException;

public class Test {
	public static void main(String[] args) {
		t1();
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
