package com.cmz.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mysql.jdbc.Buffer;

public class FileUtil {
	
	/**
	 * 每一块50M
	 */
	public final static int per_block_len = 50*1024*1024;
	
	/**
	 * 切分文件
	 * @param path
	 * @param num
	 */
	public static void splitFile(String filePath,String outDir){
		Path path = Paths.get(filePath);
		//try {
			Long len = (Long)path.toFile().length();//Files.getAttribute(path,"basic:size");
			int num = 1;
			int last_block_len = 0;
			if(per_block_len<len){
				num = (int)(len/per_block_len);
				last_block_len = (int)(len%per_block_len);
			}
			//com.google.common.io.Files.newReader(path.toFile(), Charset.forName("UTF-8"));

			try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
				for(int i=0;i<num;i++){
					try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outDir+"/"+path.getFileName()+i), "UTF8"))){
						char[] cbuf = new char[per_block_len];
						reader.read(cbuf, 0, per_block_len);
						writer.write(cbuf, 0, per_block_len);
						writer.flush();
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
				//最后一块
				try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outDir+"/"+path.getFileName()+num), "UTF8"))){
					char[] cbuf = new char[last_block_len];
					reader.read(cbuf, 0, last_block_len);
					writer.write(cbuf, 0, last_block_len);
					writer.flush();
				}catch (Exception e) {
					// TODO: handle exception
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	
	public static void merge(String inDir){
		Path path = Paths.get(inDir);
		try {
			DirectoryStream<Path> files = Files.newDirectoryStream(path,(p)->p.getFileName().toString().startsWith(".")==false);
			long countLen=0;
			for(Path file:files){
				try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toString())))){
					try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inDir+"/jdk-8u73-linux-x64.tar.gz"), "UTF8"))){
						long len = (Long)file.toFile().length();
						
						char[] cbuf = new char[(int)len];
						reader.read(cbuf, 0, (int)len);
						writer.write(cbuf, (int)countLen, (int)len);
						writer.flush();
						countLen = countLen + len;
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String filePath = "/Users/chenmingzhou/cmz/jdk-8u73-linux-x64.tar.gz";
		String outDir = "/Users/chenmingzhou/cmz/ebook/centos";
		splitFile(filePath,outDir);
		//merge(outDir);
	}
}
