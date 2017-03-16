package com.collection;

import java.util.*;

class LinkedListDemo {
	public static void main(String args[]) {
		LinkedList<String> lis = new LinkedList<String>();
		lis.add("Hello");
		lis.add("Linked List");
		lis.add("Demo");
		lis.add(null);
		for (String s : lis)
			System.out.println(s);
		// as a Stack (LIFO order)
		LinkedList<Integer> st = new LinkedList<Integer>();
		st.push(new Integer(1));
		st.push(new Integer(2));
		st.push(new Integer(3));
		st.add(new Integer(4));
		System.out.println("Object popped: " + st.pop());

		System.out.println("Object popped: " + st.pop());
		System.out.println("Object popped: " + st.pop());
		System.out.println("Object popped: " + st.pop());
		LinkedList<Long> l = new LinkedList<Long>();
		// as queue (FIFO order)
		l.add(new Long(1));
		l.add(new Long(2));
		l.add(new Long(3));
		l.add(new Long(4));
		System.out.println("Queue : " + l);
		System.out.println("head of queue: " + l.peek());
		System.out.println("head of queue removed and returned: " + l.poll());
		System.out.println("Queue : " + l);
		// as a double ended queue
		// insertion and deleltion at both ends
		l.addFirst(new Long(0));
		System.out.println("Double ended Queue : " + l);
		l.addLast(new Long(5));
		System.out.println("Double ended Queue : " + l);
		System.out.println("head of queue removed and returned:" + l.removeFirst());
		System.out.println("tail of Queue removed and returned:" + l.removeLast());
		System.out.println("Double ended Queue : " + l);
	}
}
