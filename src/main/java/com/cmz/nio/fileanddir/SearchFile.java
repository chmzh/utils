package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class SearchFile implements FileVisitor {
	private final Path searchedFile;
	public boolean found;

	public SearchFile(Path searchedFile) {
		this.searchedFile = searchedFile;
		this.found = false;
	}

	void search(Path file) throws IOException {
		Path name = file.getFileName();
		if (name != null && name.equals(searchedFile)) {
			System.out.println("Searched file was found: " + searchedFile + " in " + file.toRealPath().toString());
			found = true;
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
		if (!found) {
			return FileVisitResult.CONTINUE;
		} else {
			return FileVisitResult.TERMINATE;
		}
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) {
		// report an error if necessary
		return FileVisitResult.CONTINUE;
	}

	public static void main(String[] args) {
		Path searchFile = Paths.get("story.txt");
		SearchFile walk = new SearchFile(searchFile);
		EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		Path path = FileSystems.getDefault().getPath("");
		//for (Path root : dirs) {
			if (!walk.found) {
				try {
					Files.walkFileTree(path, opts, Integer.MAX_VALUE, walk);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//}
		if (!walk.found) {
			System.out.println("The file " + searchFile + " was not found!");
		}
	}
}
