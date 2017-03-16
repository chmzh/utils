package com.nio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TmpFile {
	public static void main(String[] args) {
		/*
		Path basedir = FileSystems.getDefault().getPath("/Users/chenmingzhou/tmp");
		String tmp_file_prefix = "rafa_";
		String tmp_file_sufix = ".txt";
		try {
			final Path tmp_file = Files.createTempFile( tmp_file_prefix, tmp_file_sufix);
			File file = tmp_file.toFile();
			file.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//Path basedir = FileSystems.getDefault().getPath("C:/rafaelnadal/tmp");
		/*
		String tmp_file_prefix = "rafa_";
		String tmp_file_sufix = ".txt";
		Path tmp_file = null;
		try {
		    tmp_file = Files.createTempFile( tmp_file_prefix, tmp_file_sufix);
		} catch (IOException e) {
		    System.err.println(e);
		}
		try (OutputStream outputStream = Files.newOutputStream(tmp_file,
		                                      StandardOpenOption.DELETE_ON_CLOSE);
		     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
		     //simulate some I/O operations over the temporary file by sleeping 10 seconds
		     //when the time expires, the temporary file is deleted
		     Thread.sleep(10000);
		     //operations done
		} catch (IOException | InterruptedException e) {
		     System.err.println(e);
		}
		*/
		
//		Path path = FileSystems.getDefault().getPath("/Users/chenmingzhou/tmp", "rafa_1.jpg");
//		//delete the file
//		try {
//		     Files.delete(path);
//		     Files.deleteIfExists(path);
//		} catch (IOException |
//		         SecurityException e) {
//		     System.err.println(e);
//		}
		
		for(int i=0;i<1;i++){
			new Thread(new CopyTask()).start();
		}
		
	}
	
	
	static class CopyTask implements Runnable{
		@Override
		public void run() {
			Path path = Paths.get("/Users/chenmingzhou/cmz/ebook/CentOS-7.0-1406-x86_64-DVD.iso");
			Path path2 = Paths.get("/Users/chenmingzhou/cmz/ebook/CentOS-7.0-1406-x86_64-DVD.iso.copy");
			try {
				Files.copy(path, path2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
