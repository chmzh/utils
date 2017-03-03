package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

public class FileOwner {
	public static void main(String[] args) {
		test2();
	}
	
	//Set a File Owner Using Files.setOwner()
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
	
	//Set a File Owner Using FileOwnerAttributeView.setOwner()
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
}
