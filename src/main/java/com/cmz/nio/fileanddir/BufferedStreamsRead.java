package com.cmz.nio.fileanddir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BufferedStreamsRead {
	public static void main(String[] args) {
		test1();
	}
	
	/**
	 * Using the newBufferedReader() Method
	 */
	private static void test1(){
		Path wiki_path = Paths.get("wiki.txt");

		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(wiki_path, charset)) {
		     String line = null;
		     while ((line = reader.readLine()) != null) {
		             System.out.println(line);
		     }
		} catch (IOException e) {
		     System.err.println(e);
		}
	}
}
