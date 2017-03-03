package com.cmz.nio.fileanddir;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileUtil {
	
	public static void main(String[] args) {
		test2();
	}
	/**
	 * Checking for the Existence of a File or Directory
	 */
	private static void test1(){
		Path path = FileSystems.getDefault().getPath("story.txt");
		boolean path_exists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
		System.out.println("文件是否存在:"+path_exists);
		boolean path_noexists = Files.notExists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
		
	}
	
	/**
	 * Checking File Accessibility
	 */
	private static void test2(){
		Path path = FileSystems.getDefault().getPath("story.txt");
		boolean is_readable = Files.isReadable(path);
		boolean is_writable = Files.isWritable(path);
		boolean is_executable = Files.isExecutable(path);
		boolean is_regular = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
		if ((is_readable) && (is_writable) && (is_executable) && (is_regular)) {
		     System.out.println("The checked file is accessible!");
		} else {
		     System.out.println("The checked file is not accessible!");
		}
		boolean is_accessible = Files.isRegularFile(path) & Files.isReadable(path) &
		                        Files.isExecutable(path) & Files.isWritable(path);
		if (is_accessible) {
		    System.out.println("The checked file is accessible!");
		} else {
		    System.out.println("The checked file is not accessible!");
		}
	}
}
