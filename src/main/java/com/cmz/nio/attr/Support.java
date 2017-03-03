package com.cmz.nio.attr;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;

public class Support {
	public static void main(String[] args) {
		FileSystem fs = FileSystems.getDefault();
		for (FileStore store : fs.getFileStores()) {
		   boolean supported = store.supportsFileAttributeView(BasicFileAttributeView.class);
		   System.out.println(store.name() + " ---" + supported);
		}
		
		Path path = Paths.get("story.txt");
		try {
		    FileStore store = Files.getFileStore(path);
		    boolean supported = store.supportsFileAttributeView("basic");
		    System.out.println(store.name() + " ---" + supported);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
