package com.cmz.interrupt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TaskRunner implements Runnable {
    private BlockingQueue<Runnable> queue;

    public TaskRunner(BlockingQueue<Runnable> queue) { 
        this.queue = queue; 
    }

    public void run() { 
        try {
             while (true) {
            	 	Runnable task = queue.take();
                 task.run();;
             }
         }
         catch (InterruptedException e) { 
             // Restore the interrupted status
             Thread.currentThread().interrupt();
         }
    }
    
    public static void main(String[] args) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
		queue.add(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("run");
				
			}
		});
		TaskRunner runner = new TaskRunner(queue);
		Thread thread = new Thread(runner);
		thread.start();
		
		thread.interrupt();
	}
}
