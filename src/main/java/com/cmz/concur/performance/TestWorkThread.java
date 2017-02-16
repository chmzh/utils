package com.cmz.concur.performance;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestWorkThread {

	
	public static void main(String[] args) {
		test2();
	}
	
	
	
	public static void test1(){
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
		Random random = new Random();
		for(int i=0;i<20;i++){
			int sleep = random.nextInt(10000);
			System.out.println("开始添加任务:"+i+",sleep:"+sleep);
			queue.add(new Task(i,sleep));
		}
		
		WorkerThread workerThread = new WorkerThread(queue);
		workerThread.start();
	}
	
	public static void test2(){
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		Random random = new Random();
		for(int i=0;i<20;i++){
			int sleep = random.nextInt(10000);
			System.out.println("开始添加任务:"+i+",sleep:"+sleep);
			executor.execute(new Task(i, sleep));
		}

	}
}
