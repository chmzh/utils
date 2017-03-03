package com.cmz.nio.attr;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
public class POSIXView {
	public static void main(String[] args) {
		read();
	}
	
	private static void read(){
		PosixFileAttributes attr = null;
		Path path = Paths.get("story.txt");
		try {
		    attr = Files.readAttributes(path, PosixFileAttributes.class);
		} catch (IOException e) {
		    System.err.println(e);
		}
		 System.out.println("File owner: " + attr.owner().getName());
		 System.out.println("File group: " + attr.group().getName());
		 System.out.println("File permissions: " + attr.permissions().toString());
		//Or you can use the “long way” by calling the Files.getFileAttributeView() method:
		
		try {
		    attr = Files.getFileAttributeView(path,
		                  PosixFileAttributeView.class).readAttributes();
		    System.out.println("File owner: " + attr.owner().getName());
			 System.out.println("File group: " + attr.group().getName());
			 System.out.println("File permissions: " + attr.permissions().toString());
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	private static void set(){
		Path new_path = Paths.get("story.txt");
		Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-r--r--");
		try {
		    Files.setPosixFilePermissions(new_path, permissions);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
