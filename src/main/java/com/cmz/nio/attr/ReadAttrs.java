package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
public class ReadAttrs {
	public static void main(String[] args) {
		BasicFileAttributes attr = null;
		Path path = Paths.get("story.txt");
		try {
		    attr = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (IOException e) {
		    System.err.println(e);
		}
		System.out.println("File size: " + attr.size());
		System.out.println("File creation time: " + attr.creationTime());
		System.out.println("File was last accessed at: " + attr.lastAccessTime());
		System.out.println("File was last modified at: " + attr.lastModifiedTime());
		System.out.println("Is directory? " + attr.isDirectory());
		System.out.println("Is regular file? " + attr.isRegularFile());
		System.out.println("Is symbolic link? " + attr.isSymbolicLink());
		System.out.println("Is other? " + attr.isOther());
		
		
		
		
		/**
		 * Basic attribute names are listed here:
			• lastModifiedTime
			• lastAccessTime
			• creationTime
			• size
			• isRegularFile
			• isDirectory
			• isSymbolicLink
			• isOther
			• fileKey
		 */
		path = Paths.get("story.txt");
		try {
		    long size = (Long)Files.getAttribute(path, "basic:size", NOFOLLOW_LINKS);
		    System.out.println("Size: " + size);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
