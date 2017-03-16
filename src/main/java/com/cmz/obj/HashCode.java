package com.cmz.obj;

public class HashCode {
	public static void main(String[] args) {
		Person person = new Person();
		person.setId(1);
		System.out.println(System.identityHashCode(person));
		
		System.out.println(person.hashCode());
		
		person = new Person();
		person.setId(3);
		System.out.println(System.identityHashCode(person));
		
		System.out.println(person.hashCode());
	}
	
	
}
class Person{
	private int id;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	@Override
	public int hashCode() {
		return String.valueOf(id).hashCode();
	}
}
