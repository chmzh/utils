package com.cmz.nio.raf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Set;

public class WriteWithFileAttr {
	public static void main(String[] args) {
		 Path path = Paths.get(System.getProperty("user.dir"), "email.txt");
		 ByteBuffer buffer = ByteBuffer.wrap("Hi Rafa, I want to congratulate you for the amazingmatch that you played ... ".getBytes());
		 //create the custom permissions attribute for the email.txt file
		 Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
		 FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		 //write a file using SeekableByteChannel
		 try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path,
		                    EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.APPEND), attr)) {
		  int write = seekableByteChannel.write(buffer);
		  System.out.println("Number of written bytes: " + write);
		 } catch (IOException ex) {
		   System.err.println(ex);
		 }
		 buffer.clear();
		}
}
