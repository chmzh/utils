package com.cmz.nio.attr;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Set;

public class ListAttr {
	public static void main(String[] args) {
		FileSystem fs = FileSystems.getDefault();
		Set<String> views = fs.supportedFileAttributeViews();
		for (String view : views) {
		      System.out.println(view);
		}
	}
}
