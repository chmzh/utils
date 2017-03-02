package com.cmz.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class Write {
	public static void main(String[] args) {
		  ByteBuffer buffer = ByteBuffer.wrap("The win keeps Nadal at the top of the heap in men'stennis, at least for a few more weeks. The world No2, Novak Djokovic, dumped out here in thesemi-finals by a resurgent Federer, will come hard at them again at Wimbledon but there ismuch to come from two rivals who, for seven years, have held all pretenders atbay.".getBytes());
		  Path path = Paths.get("story3.txt");
		  try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path,
		       StandardOpenOption.WRITE)) {
		       Future<Integer> result = asynchronousFileChannel.write(buffer, 200);
		       while (!result.isDone()) {
		              System.out.println("Do something else while writing ...");
		}
		       System.out.println("Written done: " + result.isDone());
		       System.out.println("Bytes written: " + result.get());
		  } catch (Exception ex) {
		   System.err.println(ex);
		} }
}
