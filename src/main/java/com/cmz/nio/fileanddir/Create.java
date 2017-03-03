package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class Create {
	public static void main(String[] args) {
		Path newfile = FileSystems.getDefault().getPath("SonyEricssonOpen.txt");
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		try {
			Files.createFile(newfile, attr);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
