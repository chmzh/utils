package com.io;

import java.io.FileReader;
import java.io.IOException;

public class Reader {
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader("file.txt");
		int n;
		while((n=reader.read())!=-1){
			System.out.println((char)n);
		}
		//System.out.println((char)20320);
	}
}
