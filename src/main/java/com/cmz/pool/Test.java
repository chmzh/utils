package com.cmz.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class Test {
	//DefaultPooledObject<String> sObject = new DefaultPooledObject<String>("a");
	public static final GenericObjectPool<String> pool = new GenericObjectPool<String>(new Test.StringPooledObjectFactory());
	public static void main(String[] args) {
		try {
			pool.addObject();
			String string = pool.borrowObject();
			System.out.println(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class StringPooledObjectFactory implements PoolableObjectFactory<String>{

		

		@Override
		public String makeObject() throws Exception {
			// TODO Auto-generated method stub
			return "abc";
		}

		@Override
		public void destroyObject(String obj) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean validateObject(String obj) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void activateObject(String obj) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void passivateObject(String obj) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}
}
