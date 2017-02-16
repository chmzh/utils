package com.cmz.concur.performance;

public class Task implements Runnable{

	private int taskId;
	private int sleep;
	
	public Task(int taskId,int sleep) {
		this.taskId = taskId;
		this.sleep = sleep;
	}
	public int getTaskId() {
		return taskId;
	}
	public int getSleep() {
		return sleep;
	}
	@Override
	public void run() {

//			for(int i=0;i<Integer.MAX_VALUE;i++){
//				
//			}
		try {
			Thread.sleep(sleep);
			System.out.println("任务完成:"+taskId+",sleep:"+sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
