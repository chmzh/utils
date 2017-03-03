package com.cmz.nio.fileanddir;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class DirUtil {
	public static void main(String[] args) {
		test1();
	}
	/**
	 * Listing File System Root Directories
	 */
	private static void test1(){
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path name : dirs) {
		     System.out.println(name);
		}
	}
}
