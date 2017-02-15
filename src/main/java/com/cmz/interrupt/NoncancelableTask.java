package com.cmz.interrupt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NoncancelableTask {
	
	public static void main(String[] args) {
		BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
		Thread thread = new Thread(new MyThread(queue));
		thread.start();
		thread.interrupt();
	}
	
	
	static class MyThread implements Runnable{
		BlockingQueue<Task> queue;
		public MyThread(BlockingQueue<Task> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			getNextTask(queue);
			
		}
		public Task getNextTask(BlockingQueue<Task> queue) {
	        boolean interrupted = false;
	        try {

	                try {
	                //	while (!Thread.currentThread().isInterrupted()) {
	                		return queue.take();
					//}
	                		
	                    
	                } catch (InterruptedException e) {
	                	    //Thread.currentThread().interrupt();

	                    interrupted = true;

	                    // fall through and retry
	                }
	        } finally {
	            if (interrupted){
	            		System.out.println(interrupted);
	            }
	        }
	        return null;
	    }
	}
	
    

    interface Task {
    }
}

