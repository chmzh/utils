package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileUtil {

	public static void main(String[] args) {
		test4();
	}

	/**
	 * Checking for the Existence of a File or Directory
	 */
	private static void test1() {
		Path path = FileSystems.getDefault().getPath("story.txt");
		boolean path_exists = Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
		System.out.println("文件是否存在:" + path_exists);
		boolean path_noexists = Files.notExists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });

	}

	/**
	 * Checking File Accessibility
	 */
	private static void test2() {
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
		boolean is_accessible = Files.isRegularFile(path) & Files.isReadable(path) & Files.isExecutable(path)
				& Files.isWritable(path);
		if (is_accessible) {
			System.out.println("The checked file is accessible!");
		} else {
			System.out.println("The checked file is not accessible!");
		}
	}

	/**
	 * Checking If Two Paths Point to the Same File
	 */
	private static void test3() {
		Path path_1 = FileSystems.getDefault().getPath("story.txt");
		Path path_2 = FileSystems.getDefault().getPath("story.txt");
		Path path_3 = FileSystems.getDefault().getPath("story2.txt");
		try {
			boolean is_same_file_12 = Files.isSameFile(path_1, path_2);
			boolean is_same_file_13 = Files.isSameFile(path_1, path_3);
			boolean is_same_file_23 = Files.isSameFile(path_2, path_3);
			System.out.println("is same file 1&2 ? " + is_same_file_12);
			System.out.println("is same file 1&3 ? " + is_same_file_13);
			System.out.println("is same file 2&3 ? " + is_same_file_23);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Checking the File Visibility
	 */
	private static void test4() {
		Path path = FileSystems.getDefault().getPath("story.txt");
		try {
			boolean is_hidden = Files.isHidden(path);
			System.out.println("Is hidden ? " + is_hidden);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
