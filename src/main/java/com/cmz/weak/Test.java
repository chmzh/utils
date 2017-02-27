package com.cmz.weak;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	//-Xms:1024k -Xmx1024k
	public static void main(String[] args) {
		ReferenceQueue<String> q = new ReferenceQueue<>();
		BlockingQueue<WeakReference<String>> queue = new LinkedBlockingQueue<>();
		
		WeakReference<String>  msg = new WeakReference<>(new String("abc"), q);
		
		
		queue.add(msg);
		
		System.gc();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Reference<? extends String> x; (x = q.poll()) != null; ){
			System.out.println(queue.size());
			queue.remove(x);
			System.out.println(x);
			System.out.println(queue.size());
		}
	}
}
