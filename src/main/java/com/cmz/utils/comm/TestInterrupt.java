package com.cmz.utils.comm;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestInterrupt {
	BlockingQueue<String> queue = new LinkedBlockingQueue<String>(11);

	class Producer implements Runnable {

		@Override
		public void run() {

			while (!Thread.interrupted()) {
				// queue.put("a");
				System.out.println("");
			}
		}

	}

	public static void main(String[] args) {
		TestInterrupt testInterrupt = new TestInterrupt();
		Producer producer = testInterrupt.new Producer();
		Thread thread = new Thread(producer);
		thread.start();
		thread.interrupt();

	}
}
