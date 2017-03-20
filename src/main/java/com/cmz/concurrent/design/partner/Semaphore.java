package com.cmz.concurrent.design.partner;

public class Semaphore implements Sync {
	protected long permits; // current number of available permits

	public Semaphore(long initialPermits) {
		permits = initialPermits;
	}

	public synchronized void release() {
		++permits;
		notify();
	}

	public void acquire() throws InterruptedException {
		if (Thread.interrupted())
			throw new InterruptedException();
		synchronized (this) {
			try {
				while (permits <= 0)
					wait();
				--permits;
			} catch (InterruptedException ie) {
				notify();
				throw ie;
			}
		}
	}

	public boolean attempt(long msecs) throws InterruptedException {
		if (Thread.interrupted())
			throw new InterruptedException();
		synchronized (this) {
			if (permits > 0) {
				--permits;
				return true;
			} else if (msecs <= 0)
				return false;
			else {
				try {
					// same as acquire but messier
					// avoid timed wait if not needed
					long startTime = System.currentTimeMillis();
					long waitTime = msecs;
					for (;;) {
						wait(waitTime);
						if (permits > 0) {
							--permits;
							return true;
						} else { // check for time-out
							long now = System.currentTimeMillis();
							waitTime = msecs - (now - startTime);
							if (waitTime <= 0)
								return false;
						}
					}
				} catch (InterruptedException ie) {
					notify();
					throw ie;
				}
			}
		}
	}
}
