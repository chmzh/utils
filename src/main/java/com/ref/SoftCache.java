package com.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentMap;

public class SoftCache<V> {
	//缓存
	private ConcurrentMap<Object, Entry> cache;
	//引用队列
	private ReferenceQueue<V> queue;
	
	public SoftCache(){
		
	}
	
	private class Entry extends SoftReference<V>{
		public Entry(V referent) {
			super(referent, queue);
		}
		
	}
	
}
