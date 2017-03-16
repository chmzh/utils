package com.ref;

import java.util.LinkedList;
import java.util.List;

public class CacheTest {

	private static List<Employee> employees = new LinkedList<Employee>();
	
	public static void main(String[] args) throws InterruptedException {


		
		cache1();

	}

	public static void cache1(){
		EmployeeCache cache = EmployeeCache.getInstance();

		for(int i=0;i<10000000;i++) {
			cache.getEmployee(""+i);
		} 
	}
	
	public static void cache(){
		while (true) {
			employees.add(new Employee("0"));
			
		}
	}
	
}
