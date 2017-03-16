package com.collection;

import java.io.*;

class RuntimeDemo {
	public static void main(String args[]) {
		try { //
			Runtime r = Runtime.getRuntime();
			System.out.println("Free Memory" + r.freeMemory()/(1024*1024));
			System.out.println("Total Memory" + r.totalMemory()/(1024*1024));
			/*
			// for opening a new dos prompt
			Process pr1 = r.exec("cmd /c start");
			// for creating a new process opening notepad
			Process pr2 = r.exec("notepad RuntimeDemo.java");
			// creates a new command to be executed
			Process pr3 = r.exec("cmd /c dir");
			InputStream is = pr3.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(line);
				*/
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
