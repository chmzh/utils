package com.cmz.nio.fileanddir;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadSmallFile {
	public static void main(String[] args) {
		test3();
	}

	/**
	 * Reading with the readAllBytes() Method
	 */
	private static void test1() {
		Path ball_path = Paths.get("ball.png");
		try {
			byte[] ballArray = Files.readAllBytes(ball_path);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 读取文本文件
	 */
	private static void test2() {
		Path wiki_path = Paths.get("wiki.txt");
		try {
			byte[] wikiArray = Files.readAllBytes(wiki_path);
			String wikiString = new String(wikiArray, "ISO-8859-1");
			System.out.println(wikiString);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Reading with the readAllLines() Method
	 */
	private static void test3() {
		Path wiki_path = Paths.get("wiki.txt");

		Charset charset = Charset.forName("ISO-8859-1");
		try {
			List<String> lines = Files.readAllLines(wiki_path, charset);
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
