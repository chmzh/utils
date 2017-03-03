package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStoreAttr {
	public static void main(String[] args) {
		test2();
	}
	/**
	 * Get Attributes of All File Stores
	 */
	private static void test1(){
		FileSystem fs = FileSystems.getDefault();
		for (FileStore store : fs.getFileStores()) {
		  try {
		        long total_space = store.getTotalSpace() / 1024;
		        long used_space = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
		        long available_space = store.getUsableSpace() / 1024;
		        boolean is_read_only = store.isReadOnly();
		        System.out.println("--- " + store.name() + " --- " + store.type());
		        System.out.println("Total space: " + total_space);
		        System.out.println("Used space: " + used_space);
		        System.out.println("Available space: " + available_space);
		        System.out.println("Is read only? " + is_read_only);
		  } catch (IOException e) {
		      System.err.println(e);
		} }
	}
	/**
	 * Get Attributes of the File Store in Which a File Resides
	 */
	private static void test2(){
		Path path = Paths.get("story.txt");
		try {
		    FileStore store = Files.getFileStore(path);
		   long total_space = store.getTotalSpace() / 1024;
		   long used_space = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
		   long available_space = store.getUsableSpace() / 1024;
		   boolean is_read_only = store.isReadOnly();
		   System.out.println("--- " + store.name() + " --- " + store.type());
		   System.out.println("Total space: " + total_space);
		   System.out.println("Used space: " + used_space);
		   System.out.println("Available space: " + available_space);
		   System.out.println("Is read only? " + is_read_only);
		} catch (IOException e) {
		   System.err.println(e);
		}
	}
}
