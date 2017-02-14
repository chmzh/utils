package com.cmz.concur.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ExecutorService;

public class Test {
	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("第1组");
		ExecutorService executorService = Executors.newCachedThreadPool(new MyThreadFactory(group, "线程-"));
		for(int i=0;i<10;i++){
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					
					
				}
				
			}
		});
		}
	}
}
