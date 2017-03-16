package com.io;

import java.io.*;

class BufferedInOutStreamDemo {
	public static void main(String args[]) throws IOException {
		String file1 = "file.txt";
		String file2 = "out.txt";
		FileInputStream fis = new FileInputStream(file1);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int n = fis.available();
		bis.mark(n);
		System.out.println("Marked the stream");
		byte b[] = new byte[n];
		byte b1[] = new byte[n];
		bis.read(b);
		System.out.println("Contents of file.txt" + file1 + ":" + new String(b));
		System.out.println("Resetting the stream");
		bis.reset();
		System.out.println("Reading the stream again from the marked point");
		bis.read(b1);
		System.out.println(new String(b1));
		bis.close();
		fis.close();
		System.out.println("Writing contents to : " + file2);
		FileOutputStream fos = new FileOutputStream(file2);
		BufferedOutputStream out = new BufferedOutputStream(fos);
		out.write(b);
		System.out.println("Contents written");
		out.close();
		fos.close();
	}
}
