package com.cmz.nio.fileanddir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BufferedStreamsWrite {
	public static void main(String[] args) {
		test1();
	}
	
	/**
	 * Using the newBufferedWriter() Method
	 */
	private static void test1(){
		Path wiki_path = Paths.get("wiki.txt");
		Charset charset = Charset.forName("UTF-8");
		String text = "\nVamos Rafa!";
		try (BufferedWriter writer = Files.newBufferedWriter(wiki_path, charset,
		                                                                 StandardOpenOption.APPEND)) {
		     writer.write(text);
		} catch (IOException e) {
		     System.err.println(e);
		}
	}
}
