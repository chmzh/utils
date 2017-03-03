package com.cmz.nio.fileanddir;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemporaryDir {
	public static void main(String[] args) {
		test3();
	}

	/**
	 * Creating a Temporary Directory
	 */
	private static void test1() {

		String default_tmp = System.getProperty("java.io.tmpdir");
		System.out.println(default_tmp);

		String tmp_dir_prefix = "nio_";
		try {
			// passing null prefix
			Path tmp_1 = Files.createTempDirectory(null);
			System.out.println("TMP: " + tmp_1.toString());
			// set a prefix
			Path tmp_2 = Files.createTempDirectory(tmp_dir_prefix);
			System.out.println("TMP: " + tmp_2.toString());
		} catch (IOException e) {
			System.err.println(e);
		}

		Path basedir = FileSystems.getDefault().getPath("");
		tmp_dir_prefix = "rafa_";
		try {
			// create a tmp directory in the base dir
			Path tmp = Files.createTempDirectory(basedir, tmp_dir_prefix);
			System.out.println("TMP: " + tmp.toString());

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.out.println("Deleting the temporary folder ...");
					try (DirectoryStream<Path> ds = Files.newDirectoryStream(tmp)) {
						for (Path file : ds) {
							Files.delete(file);
						}
						Files.delete(tmp);
					} catch (IOException e) {
						System.err.println(e);
					}
					System.out.println("Shutdown-hook completed...");
				}
			});
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	/**
	 * Deleting a Temporary Directory with the deleteOnExit() Method
	 */
	private static void test3() {
		Path basedir = FileSystems.getDefault().getPath("");
		String tmp_dir_prefix = "rafa_";
		try {
			// create a tmp directory in the base dir
			Path tmp_dir = Files.createTempDirectory(basedir, tmp_dir_prefix);
			File asFile = tmp_dir.toFile();
			asFile.deleteOnExit();
			// simulate some I/O operations over the temporary file by sleeping
			// 10 seconds
			// when the time expires, the temporary file is deleted
			// EACH CREATED TEMPORARY ENTRY SHOULD BE REGISTERED FOR DELETE ON
			// EXIT
			Thread.sleep(10000);
			// operations done
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
	}
}
