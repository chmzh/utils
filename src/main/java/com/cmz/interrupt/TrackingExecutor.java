package com.cmz.interrupt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TrackingExecutor
 * <p/>
 * ExecutorService that keeps track of cancelled tasks after shutdown
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TrackingExecutor extends AbstractExecutorService {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		TrackingExecutor trackingExecutor = new TrackingExecutor(executorService);
		for(int i=0;i<20;i++){
			trackingExecutor.execute(new Task(i));
		}
		
		List<Runnable> tasks = trackingExecutor.shutdownNow();
		for(Runnable runnable : tasks){
			Task task = (Task)runnable;
			System.out.println(task.getTaskId());
		}
	}
	
	
	
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown =
            Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    public void shutdown() {
        exec.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    public boolean isShutdown() {
        return exec.isShutdown();
    }

    public boolean isTerminated() {
        return exec.isTerminated();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    public List<Runnable> getCancelledTasks() {
        if (!exec.isTerminated())
            throw new IllegalStateException(/*...*/);
        return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }

    public void execute(final Runnable runnable) {
        exec.execute(new Runnable() {
            public void run() {
                try {
                    runnable.run();
                } finally {
                    if (isShutdown()
                            && Thread.currentThread().isInterrupted()){
                    		Task task = (Task)runnable;
                    		System.out.println("正在运行被中断的线程："+task.getTaskId());
                    		tasksCancelledAtShutdown.add(runnable);
                    }
                        
                }
            }
        });
    }
}

class Task implements Runnable{

	private int taskId;
	
	public Task(int taskId) {
		this.taskId = taskId;
	}
	public int getTaskId() {
		return taskId;
	}
	@Override
	public void run() {

			for(int i=0;i<1000000;i++){
				
			}
		
	}
	
}
