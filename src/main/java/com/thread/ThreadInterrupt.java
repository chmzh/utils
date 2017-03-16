package com.thread;
class ThreadInterrupt extends Thread {
	boolean interrupt = false;
	String name;

	ThreadInterrupt(String n) {
		super(n);
		name = n;
	}

	public void run() {
		while (!interrupt) {
			System.out.println("Thread running: " + name + " state: " + getState());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread Interrupted:" + name + "state:" + getState());
			}
		}
		System.out.println("Thread exiting under request: " + name + "state: " + getState());
	}

	public static void main(String args[]) throws Exception {
		ThreadInterrupt thread = new ThreadInterrupt("InterruptExample");
		System.out.println("Starting Thread: " + thread.name + "state: " + thread.getState());
		thread.start();
		Thread.sleep(3000);
		System.out.println("Stopping Thread: " + thread.name + "state: " + thread.getState());
		thread.interrupt = true;
		thread.interrupt();
		System.out.println(thread.name + " state: " + thread.getState());
		Thread.sleep(3000);
		System.out.println("Exiting application state: " + thread.getState());
		System.exit(0);
	}
}
