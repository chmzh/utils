package com.cmz.concur.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class MyThreadFactory implements ThreadFactory {

	private final String name;
	private final ThreadGroup group;
	private final AtomicLong count;
	public MyThreadFactory(ThreadGroup group,String name) {
		this.group = group;
		this.name = name;
		this.count = new AtomicLong(0);
	}
	
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		return new Thread(group, r, name+this.count.getAndIncrement());
	}

}
