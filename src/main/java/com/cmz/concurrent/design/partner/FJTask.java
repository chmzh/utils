package com.cmz.concurrent.design.partner;

public abstract class FJTask implements Runnable {
	boolean isDone() {
		return true;
	}

	void cancel() {
	}

	void fork() {
	}

	void start() {
	}

	static void yield() {
	}

	void join() {
	}

	static void invoke(FJTask t) {
	}

	static void coInvoke(FJTask t, FJTask u) {
	}

	static void coInvoke(FJTask[] tasks) {
	} // coInvoke all

	void reset() {
	} // Clear to allow reuse
}
