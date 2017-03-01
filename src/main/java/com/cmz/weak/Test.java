package com.cmz.weak;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	//-Xms:1024k -Xmx1024k
	public static void main(String[] args) {
    	ReferenceQueue<byte[]> queue = new ReferenceQueue<byte[]>();
        BlockingQueue<WeakReference<byte[]>> queue2 = new LinkedBlockingQueue<>();
//        Map<Reference<byte[]>, String> map = new HashMap<Reference<byte[]>, String>();
//        map.put(new WeakReference<>(new byte[1024*500],queue), "abc");
//        map.put(new WeakReference<>(new byte[1024*500],queue), "abc");
//        map.put(new WeakReference<>(new byte[1024*500],queue), "abc");
        
//        for(int i=0;i<10;i++){
//        	Thread thread = new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					while(true){
//						queue2.add(new WeakReference<>(new byte[1],queue));
//					}
//					
//				}
//			});
//        }
        
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        
        //map.put(new WeakReference<>(new byte[1024*500],queue), "abc");
    	
        

//        queue2.add(new WeakReference(new byte[1024*500],queue));
//        queue2.add(new WeakReference(new byte[1024*500],queue));
//        queue2.add(new WeakReference(new byte[1024*500],queue));
//        queue2.add(new WeakReference(new byte[1024*500],queue));
        //byte[] bytes = new byte[1024*500];
//        System.gc();
//        try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        System.gc();
        for (Object x; (x = queue.poll()) != null; ){
        	System.out.println(x);
        }
        System.out.println("============================");
        for(Reference<byte[]> entry:queue2){
        	System.out.println(entry.get());
        }
        
//        for(Map.Entry<Reference<byte[]>, String> entry:map.entrySet()){
//        	System.out.println(entry.getKey());
//        }
	}
}
