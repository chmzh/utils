package com.cmz.string;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		 String commaSeparatedNumbers = numbers.stream()
		     .map(i -> i.toString())
		     .collect(Collectors.joining(", "));
		 System.out.println(commaSeparatedNumbers);
		 
		 StringJoiner sj = new StringJoiner(":", "[", "]");
		 sj.add("George").add("Sally").add("Fred");
		 String desiredString = sj.toString();
		 System.out.println(desiredString);
	}
}
