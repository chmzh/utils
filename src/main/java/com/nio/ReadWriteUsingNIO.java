package com.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ReadWriteUsingNIO {
	public static void main(String args[]) {
		try {
			// Use a mapped file to read a text file
			FileInputStream fis = new FileInputStream("/Users/chenmingzhou/gradle-2.2.1-all.zip");
			FileChannel fc = fis.getChannel();
			long fs = fc.size();
			MappedByteBuffer mBuf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fs);
			System.out.println();
			for (int i = 0; i < fs; i++)
				System.out.print((char) mBuf.get());
			fc.close();
			fis.close();
			// write to a file using nio
			String str = "welcome, writing to a file using nio package";
			FileOutputStream fos = new FileOutputStream("samplenio.txt");
			FileChannel fc1 = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(str.length());
			byte[] b = str.getBytes();
			buffer.put(b);
			buffer.flip();
			fc1.write(buffer);
			fc1.close();
			fos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
