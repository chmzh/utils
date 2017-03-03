package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class Walk {
	
	public static void main(String[] args) {
		test1();
	}
	
	private static void test1() {
		Path listDir = Paths.get("src"); // define the starting file tree
		ListTree walk = new ListTree();
		try {
			Files.walkFileTree(listDir, walk);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}



class ListTree extends SimpleFileVisitor<Path> {
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
		System.out.println("==============Visited directory==========: " + dir.toString());
		try(DirectoryStream<Path> path = Files.newDirectoryStream(dir)) {
			for (Path file : path) {
				System.out.println(file.getFileName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {

		System.out.println(exc);
		return FileVisitResult.CONTINUE;
	}
}