package com.cmz.nio.links;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class Symbolic {

	public static void main(String[] args) {
		test3();
	}

	/**
	 * Creating a Symbolic Link
	 */
	private static void test1() {
		Path link = FileSystems.getDefault().getPath("story_symbolic.txt");
		Path target = FileSystems.getDefault().getPath("story.txt");
		try {
			// PosixFileAttributes attrs = Files.readAttributes(target,
			// PosixFileAttributes.class);
			// FileAttribute<Set<PosixFilePermission>> attr =
			// PosixFilePermissions.asFileAttribute(attrs.permissions());
			// Files.createSymbolicLink(link, target, attr);
			Files.createSymbolicLink(link, target);
			/*
			 * FileTime lm = (FileTime) Files.getAttribute(target,
			 * "basic:lastModifiedTime", NOFOLLOW_LINKS); FileTime la =
			 * (FileTime) Files.getAttribute(target, "basic:lastAccessTime",
			 * NOFOLLOW_LINKS); Files.setAttribute(link,
			 * "basic:lastModifiedTime", lm, NOFOLLOW_LINKS);
			 * Files.setAttribute(link, "basic:lastAccessTime", la,
			 * NOFOLLOW_LINKS);
			 */
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("Permission denied!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("An unsupported operation was detected!");
			}
			if (e instanceof IOException) {
				System.err.println("An I/O error occurred!");
			}
			System.err.println(e);
		}
	}

	/**
	 * Checking a Symbolic Link
	 * 
	 * @param link
	 */
	private static void test2(Path link, Path target) {
		boolean link_isSymbolicLink_1 = Files.isSymbolicLink(link);
		boolean target_isSymbolicLink_1 = Files.isSymbolicLink(target);
		System.out.println(link.toString() + " is a symbolic link ? " + link_isSymbolicLink_1);
		System.out.println(target.toString() + " is a symbolic link ? " + target_isSymbolicLink_1);

		// 第二种方式
		try {
			boolean link_isSymbolicLink_2 = (boolean) Files.getAttribute(link, "basic:isSymbolicLink");
			boolean target_isSymbolicLink_2 = (boolean) Files.getAttribute(target, "basic:isSymbolicLink");
			System.out.println(link.toString() + " is a symbolic link ? " + link_isSymbolicLink_2);
			System.out.println(target.toString() + " is a symbolic link ? " + target_isSymbolicLink_2);
		} catch (IOException | UnsupportedOperationException e) {
			System.err.println(e);
		}
	}

	/**
	 * Checking If a Link and a Target Point to the Same File
	 */
	private static void test3() {
		Path link = FileSystems.getDefault().getPath("story_symbolic.txt");
		Path target = FileSystems.getDefault().getPath("story.txt");
//		try {
//			Files.createSymbolicLink(link, target);
//		} catch (IOException | UnsupportedOperationException | SecurityException e) {
//		}
		try {
			Path linkedpath = Files.readSymbolicLink(link);
			System.out.println(linkedpath.toString());
			System.out.println(Files.isSameFile(link,target));
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
