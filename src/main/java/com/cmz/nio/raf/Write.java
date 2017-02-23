package com.cmz.nio.raf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Write {
	 public static void main(String[] args) {
		   Path path = Paths.get(System.getProperty("user.dir"), "story.txt");
		   //write a file using SeekableByteChannel
		   try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path,
		                EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING))) {
		    ByteBuffer buffer = ByteBuffer.wrap("Rafa Nadal produced another masterclass of clay-court tennis to win his fifth French Open title ...".getBytes());
		    int write = seekableByteChannel.write(buffer);
		    System.out.println("Number of written bytes: " + write);
		    buffer.clear();
		   } catch (IOException ex) {
		     System.err.println(ex);
		   }
		}
}
