package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class DirUtil {
	public static void main(String[] args) {
		test2();
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
	
	/**
	 * Creating a New Directory
	 */
	private static void test2(){
		Path newdir = FileSystems.getDefault().getPath("test");
		try {
			Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
			FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		    Files.createDirectory(newdir,attr);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
