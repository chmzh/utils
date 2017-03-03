package com.cmz.nio.links;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class Symbolic {

	public static void main(String[] args) {
		test1();
	}

	/**
	 * Creating a Symbolic Link
	 */
	private static void test1() {
		Path link = FileSystems.getDefault().getPath("story_symbolic.txt");
		Path target = FileSystems.getDefault().getPath("story.txt");
		try {
			//PosixFileAttributes attrs = Files.readAttributes(target, PosixFileAttributes.class);
			//FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(attrs.permissions());
			//Files.createSymbolicLink(link, target, attr);
			Files.createSymbolicLink(link, target);
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
}
