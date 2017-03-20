package com.cmz.concurrent.design.partner;

public class CondVar {
	protected final Sync mutex;
	 public CondVar(Sync lock) { mutex = lock; }
	 public void await() throws InterruptedException{
		 
	 }
	 public boolean timedwait(long ms) throws InterruptedException{
		 return true;
	 }
	 public void signal(){
		 // analog of notify
	 }
	 public void broadcast(){
		 // analog of notifyAll
	 }
}
