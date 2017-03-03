package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
public class FileOwner {
	public static void main(String[] args) {
		test5();
	}
	
	/**
	 * Set a File Owner Using Files.setOwner()
	 */
	private static void test1(){
		UserPrincipal owner = null;
		Path path = Paths.get("story.txt");
		try {
		    owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("chenmingzhou");
		    Files.setOwner(path, owner);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	/**
	 * Set a File Owner Using FileOwnerAttributeView.setOwner()
	 */
	private static void test2(){
		UserPrincipal owner = null;
		Path path = Paths.get("story.txt");
		FileOwnerAttributeView foav = Files.getFileAttributeView(path,
		                                           FileOwnerAttributeView.class);
		try {
		    owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("chenmingzhou");;
		    foav.setOwner(owner);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	private static void test3(){
		UserPrincipal owner = null;
		Path path = Paths.get("story.txt");
		try {
		    owner = path.getFileSystem().getUserPrincipalLookupService().
		                                 lookupPrincipalByName("apress");
		    Files.setAttribute(path, "owner:owner", owner, NOFOLLOW_LINKS);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	/**
	 * Get a File Owner Using FileOwnerAttributeView.getOwner()
	 */
	private static void test4(){
		Path path = Paths.get("story.txt");
		FileOwnerAttributeView foav = Files.getFileAttributeView(path,
		                                        FileOwnerAttributeView.class);
		try {
		    String owner = foav.getOwner().getName();
		    System.out.println(owner);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	/**
	 * Get a File Owner Using Files.getAttribute()
	 */
	private static void test5(){
		Path path = Paths.get("story.txt");
		try {
		    UserPrincipal owner = (UserPrincipal) Files.getAttribute(path,
		                                               "owner:owner", NOFOLLOW_LINKS);
		    System.out.println(owner.getName());
		    } catch (IOException e) {
		        System.err.println(e);
		    }
	}
}
