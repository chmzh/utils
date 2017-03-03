package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class WriteSmallFile {
	public static void main(String[] args) {
		test3();
	}
	/**
	 * Writing Bytes with the write() Method
	 */
	private static void test1(){
		Path ball_path = Paths.get("ball.png");
		byte[] ball_bytes = new byte[]{
		(byte)0x89,(byte)0x50,(byte)0x4e,(byte)0x47,(byte)0x0d,(byte)0x0a,(byte)0x1a,(byte)0x0a,
		(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0d,(byte)0x49,(byte)0x48,(byte)0x44,(byte)0x52,
		(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x10,
		(byte)0x08,(byte)0x02,(byte)0x00,
		(byte)0x49,(byte)0x45,(byte)0x4e,(byte)0x44,(byte)0xae,(byte)0x42,(byte)0x60,(byte)0x82
		};
		try {
		    Files.write(ball_path, ball_bytes);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	/**
	 * write string
	 */
	private static void test2(){
		Path rf_wiki_path = Paths.get("wiki.txt");

		String rf_wiki = "Rafael \"Rafa\" Nadal Parera (born 3 June 1986) is a Spanish professionaltennis " + "player and a former World No. 1. As of 29 August 2011 (2011 -08-29)[update], he isranked No. 2 " + "by the Association of Tennis Professionals (ATP). He is widely regarded asone of the greatest players " + "of all time; his success on clay has earned him the nickname	\"The King of Clay\", and has prompted " + "many experts to regard him as the greatest claycourt player of all time. Some of his best wins are:";
		try {
		    byte[] rf_wiki_byte = rf_wiki.getBytes("UTF-8");
		    Files.write(rf_wiki_path, rf_wiki_byte);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	/**
	 * Writing Lines with the write() Method
	 */
	private static void test3(){
		Path rf_wiki_path = Paths.get("wiki.txt");
		Charset charset = Charset.forName("UTF-8");
		ArrayList<String> lines = new ArrayList<>();
		lines.add("\n");
		lines.add("Rome Masters - 5 titles in 6 years");
		lines.add("Monte Carlo Masters - 7 consecutive titles (2005-2011)");
		lines.add("Australian Open - Winner 2009");
		lines.add("Roland Garros - Winner 2005-2008, 2010, 2011");
		lines.add("Wimbledon - Winner 2008, 2010");
		lines.add("US Open - Winner 2010");
		try {
		    Files.write(rf_wiki_path, lines, charset, StandardOpenOption.APPEND);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
}
