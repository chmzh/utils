package com.cmz.concurrent.design.partner;

interface Sync {
	 void acquire() throws InterruptedException;
	 void release();
	 boolean attempt(long msec) throws InterruptedException;
	}
