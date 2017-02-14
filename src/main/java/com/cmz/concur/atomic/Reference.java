package com.cmz.concur.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class Reference {
	public static void main(String[] args) {
		Student student = new Student();
		student.setId(1);
		AtomicReference<Student> studenR = new AtomicReference<Student>(student);
		Student student2 = new Student();
		student2.setId(2);
		studenR.compareAndSet(student2, student);
		System.out.println(studenR.get().getId());
	}
	
	
}
