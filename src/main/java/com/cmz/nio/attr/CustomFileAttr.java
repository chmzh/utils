package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class CustomFileAttr {
	
	public static void main(String[] args) {
		test1();
		list();
	}
	
	private static void test1(){
		Path path = Paths.get("story.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path,UserDefinedFileAttributeView.class);
		  try {
			  
			  int written = udfav.write("file.description", Charset.defaultCharset().
		               encode("This file contains private information!"));
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	private static void list(){
		Path path = Paths.get("story.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path,UserDefinedFileAttributeView.class);
		  try {
		    for (String name : udfav.list()) {
		        System.out.println(udfav.size(name) + "  " + name);
		    }
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
