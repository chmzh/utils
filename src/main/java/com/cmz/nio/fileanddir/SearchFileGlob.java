package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class SearchFileGlob implements FileVisitor {
	private final PathMatcher matcher;

	public SearchFileGlob(String glob) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
	}

	void search(Path file) throws IOException {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			System.out.println("Searched file was found: " + name + " in " + file.toRealPath().toString());
		}
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		System.out.println("Visited: " + (Path) dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		search((Path) file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		// report an error if necessary
		return FileVisitResult.CONTINUE;
	}
	
	public static void main(String[] args) {
		String glob = "*.java";
		Path fileTree = Paths.get("src");
		SearchFileGlob walk = new SearchFileGlob(glob);
		EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		try {
			Files.walkFileTree(fileTree, opts, Integer.MAX_VALUE, walk);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
