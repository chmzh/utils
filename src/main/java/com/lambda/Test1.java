package com.lambda;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws IOException {
		StringToIntMapper mapper = (b)->b.length();
		System.out.println(mapper.map("123"));
		
		System.out.println(File.separator);
		
		System.out.println(System.getProperty("line.separator"));
/*		
		File file = File.createTempFile("myTmp", null);
		DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
		outputStream.writeInt(100);
		System.out.println();
		*/
	}
}
