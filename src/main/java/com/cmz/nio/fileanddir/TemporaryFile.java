package com.cmz.nio.fileanddir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Creating Temporary Files
 * 
 * @author chenmingzhou
 *
 */
public class TemporaryFile {
	public static void main(String[] args) {
		test4();
	}

	private static void test1() {
		String tmp_file_prefix = "rafa_";
		String tmp_file_sufix = ".txt";
		try {
			// passing null prefix/suffix
			Path tmp_1 = Files.createTempFile(null, null);
			System.out.println("TMP: " + tmp_1.toString());
			// set a prefix and a suffix
			Path tmp_2 = Files.createTempFile(tmp_file_prefix, tmp_file_sufix);
			System.out.println("TMP: " + tmp_2.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static void test2() {
		Path basedir = FileSystems.getDefault().getPath("tmp");
		String tmp_file_prefix = "rafa_";
		String tmp_file_sufix = ".txt";
		try {
			Path tmp_3 = Files.createTempFile(basedir, tmp_file_prefix, tmp_file_sufix);
			System.out.println("TMP: " + tmp_3.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Deleting a Temporary File with the deleteOnExit() Method
	 */
	private static void test3() {
		Path basedir = FileSystems.getDefault().getPath("tmp");
		String tmp_file_prefix = "rafa_";
		String tmp_file_sufix = ".txt";
		try {
			final Path tmp_file = Files.createTempFile(basedir, tmp_file_prefix, tmp_file_sufix);
			File asFile = tmp_file.toFile();
			asFile.deleteOnExit();
			// simulate some I/O operations over the temporary file by sleeping
			// 10 seconds
			// when the time expires, the temporary file is deleted
			Thread.sleep(10000);
			// operations done
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Deleting a Temporary File with DELETE_ON_CLOSE
	 */
	private static void test4() {
		Path basedir = FileSystems.getDefault().getPath("tmp");
		String tmp_file_prefix = "rafa_";
		String tmp_file_sufix = ".txt";
		Path tmp_file = null;
		try {
		    tmp_file = Files.createTempFile(basedir, tmp_file_prefix, tmp_file_sufix);
		    
		} catch (IOException e) {
		    System.err.println(e);
		}
		try (OutputStream outputStream = Files.newOutputStream(tmp_file,StandardOpenOption.DELETE_ON_CLOSE);
		     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
		     //simulate some I/O operations over the temporary file by sleeping 10 seconds
		     //when the time expires, the temporary file is deleted
		     Thread.sleep(10000);
		     //operations done
		} catch (IOException | InterruptedException e) {
		     System.err.println(e);
		}
	}
}
