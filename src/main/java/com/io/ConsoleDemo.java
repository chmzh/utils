package com.io;

import java.io.*;

class ConsoleDemo {
	public static void main(String args[]) throws IOException {
		Console c = System.console();
		String user = c.readLine("Enter your username: ");
		c.printf("Welcome %1$s. Hope You had a Nice Day. ", user);
		String pno = c.readLine("\nEnter your Phone No.: ");
		c.printf("You entered %1$s as your phone Number ", pno);
		String age = c.readLine("\nEnter your Age: ");
		c.printf("name: %3$s, Age: %2$s Phone No.: %1$s", pno, age, user);
		// another way of writing to the Console
		PrintWriter out = c.writer();
		out.println("\nEnter your password");
		char[] pass = c.readPassword();
		c.printf("The password you entered is %1$s ", new String(pass));
	}
}