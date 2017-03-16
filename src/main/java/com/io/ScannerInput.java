package com.io;

import java.util.Scanner;

class ScannerInput {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		// nextInt reads the next integer till the delimiter (whitespace by
		// default)
		
		System.out.println("Enter a String:");
		String string = in.nextLine();
		System.out.println("Enter next String:");
		String string2 = in.nextLine();
		
		System.out.println("Enter First Integer");
		int number = in.nextInt();
		
		System.out.println("Enter next Float: ");
		float real = in.nextFloat();
		
		System.out.println(number + " " + string + " " + real + " " + string2);
	}
}
