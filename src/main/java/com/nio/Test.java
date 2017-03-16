package com.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class Test {
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		Path link = Paths.get("filelink.txt");
		Path target = Paths.get("file.txt");
		System.out.println(Files.exists(link));
//		try {
//			Files.createSymbolicLink(link, target);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
