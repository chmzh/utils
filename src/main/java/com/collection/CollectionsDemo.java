package com.collection;

import java.util.*;

public class CollectionsDemo {
	public static void main(String[] args) {
     List<String> l = new LinkedList<String>();
     l.add("Ignorance");
     l.add("is");
     l.add("a");
     List<String> sb = new LinkedList<String>();
     sb.add("Bliss");
     sb.add("Bliss1");
     sb.add("Bliss2");
     List<String> srch = new LinkedList<String>();
     srch.add("Bliss");
     System.out.println ("Elements in list : " + l);
// create a copy of defined list and print objects of copy list.
     Collections.copy(l,sb);
     System.out.println ("copy of list : " + l);
// find and display index of first occurrence of sublist in the list.
System.out.println("First index of 'Bliss':" + Collections.indexOfSubList(l, srch));
// replace all objects in list by a new given object.
     Collections.replaceAll(l, "Bliss", "welcome");
     System.out.println("After replace all 'Bliss': " + l);
// list in reverse order.
     Collections.reverse(l);
     System.out.println("List in reverse order: " + l);
// swaps specified element with element at 1st(second) position
     int a = l.size();
     Collections.swap(l, 1, a - 1);
     System.out.println("List after swapping : " + l);
// Replace all the element with given element using fill()
     Collections.fill(l, "Bliss");
     System.out.println("After filling all 'Bliss' in list : "+ l);
// getting an enum type of specified list through enumeration().
     Enumeration<String> e = Collections.enumeration(l);
     while (e.hasMoreElements())
     System.out.println(e.nextElement());
}
}
