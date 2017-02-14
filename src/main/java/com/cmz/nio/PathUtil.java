package com.cmz.nio;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {
	public static void main(String[] args) throws IOException {
		FileSystem fileSystem = FileSystems.getDefault();
		System.out.println(fileSystem.getPath("pom.xml").toAbsolutePath());
		
		Path path = Paths.get("../../pom.xml");
		path.toFile().createNewFile();
		System.out.println(path.toAbsolutePath());
		System.out.println(path.toAbsolutePath().normalize());
		
		path = Paths.get(System.getProperty("user.home"), "downloads", "game.exe");
		System.out.println(path);
	}
}
