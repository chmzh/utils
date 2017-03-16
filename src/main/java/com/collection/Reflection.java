package com.collection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {
	public static void main(String[] args) {
		Class class1 = A.class;
		Method[] methods = class1.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(Modifier.isPublic(method.getModifiers())+":"+method.getName());
		}
		
		System.out.println("==============================");
		
		Method[] methods1 = class1.getMethods();
		for(Method method:methods1){
			System.out.println(Modifier.isPublic(method.getModifiers())+":"+method.getName());
		}
		
	}
}
