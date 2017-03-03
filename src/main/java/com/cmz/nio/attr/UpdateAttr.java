package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
public class UpdateAttr {
	
	public static void main(String[] args) {
		
		Path path = Paths.get("story.txt");
		test2(path);
		try {
		    FileTime lastModifiedTime = (FileTime)Files.getAttribute(path,
		                                 "basic:lastModifiedTime", NOFOLLOW_LINKS);
		    FileTime creationTime = (FileTime)Files.getAttribute(path,
		                                 "basic:creationTime", NOFOLLOW_LINKS);
		    FileTime lastAccessTime = (FileTime)Files.getAttribute(path,
		                                 "basic:lastAccessTime", NOFOLLOW_LINKS);
		    System.out.println("New last modified time: " + lastModifiedTime);
		    System.out.println("New creation time: " + creationTime);
		    System.out.println("New last access time: " + lastAccessTime);
		} catch (IOException e) {
		      System.err.println(e);
		}
	}
	
	private static void test1(Path path){
		
		long time = System.currentTimeMillis();
		FileTime fileTime = FileTime.fromMillis(time);
		try {
		    Files.getFileAttributeView(path,
		    BasicFileAttributeView.class).setTimes(fileTime, fileTime, fileTime);
		} catch (IOException e) {
		    System.err.println(e);
		}
		//Updating the file’s last modified time can also be accomplished with the Files.setLastModifiedTime() method:
		time = System.currentTimeMillis();
		fileTime = FileTime.fromMillis(time);
		try {
		    Files.setLastModifiedTime(path, fileTime);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	private static void test2(Path path){
		long time = System.currentTimeMillis();
		FileTime fileTime = FileTime.fromMillis(time);
		try {
		    Files.setAttribute(path, "basic:lastModifiedTime", fileTime, NOFOLLOW_LINKS);
		    Files.setAttribute(path, "basic:creationTime", fileTime, NOFOLLOW_LINKS);
		    Files.setAttribute(path, "basic:lastAccessTime", fileTime, NOFOLLOW_LINKS);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
