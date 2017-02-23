package com.cmz.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
	private final static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
	public static void main(String[] args) {
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("5");
				
			}
		}, 0, 5, TimeUnit.SECONDS);
		
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("10");
				
			}
		}, 0, 10, TimeUnit.SECONDS);
	}
}
