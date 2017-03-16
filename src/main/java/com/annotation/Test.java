package com.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.format.TextStyle;

@Version(major = -1, minor = -2)
public class Test {

	private int a;
	public static void main(String[] args) {
		Version version = Test.class.getAnnotation(Version.class);
		System.out.println(version.major());
		
		System.out.println(Version.class.getPackage());
		Test test = new Test();
		Test.A a = test.new A();
		Field[] fields = Test.class.getDeclaredFields();
		for(Field field : fields){
			int i = field.getModifiers() & Modifier.PRIVATE;
			System.out.println(Modifier.toString(i));
			System.out.println(field.getName());
		}
		a.p();
	}
	
	class A{
		public void p(){
			System.out.println("p");
		}
	}
}
