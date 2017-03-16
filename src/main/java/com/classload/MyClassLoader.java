package com.classload;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {

	
	public MyClassLoader(URL[] urls) {
		super(urls);
	}
	
	public static void main(String[] args) {
		URL[] urls = new URL[1];
		try {
			urls[0] = new URL("file://Users/chenmingzhou/Documents/workspace19/Test/bin/");
			MyClassLoader loader = new MyClassLoader(urls);
			
			Class<?> clazz = loader.loadClass("com.classload.Person");
			
			try {
				Person person = (Person)clazz.newInstance();
				person.setName("abc");
				System.out.println(person);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
