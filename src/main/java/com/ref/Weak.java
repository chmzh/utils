package com.ref;

import java.lang.ref.WeakReference;

public class Weak {
	private static WeakReference<String> cache;
	
	public static void main(String[] args) throws InterruptedException {
		String aString = new String("abc");
		cache = new WeakReference<String>(aString);
		
		//aString = null;
		System.gc();
		//Thread.sleep(2000);
		byte[] bs = new byte[2023000];
		System.out.println(cache.get());
	}
}
