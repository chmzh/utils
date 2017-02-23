package com.cmz.thread;

import java.lang.Thread.UncaughtExceptionHandler;

import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import com.google.common.util.concurrent.UncheckedExecutionException;

public class Test {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				throw new RuntimeException();
				
			}
		});
		
		thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}
}
