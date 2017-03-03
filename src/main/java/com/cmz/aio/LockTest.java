package com.cmz.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class LockTest {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer
				.wrap("Argentines At Home In Buenos Aires Cathedral\n TheCopa Claro is the third stop of the four-tournament Latin American swing, and is contested onclay at the Buenos Aires Lawn Tennis Club, known as the Cathedral of Argentinean tennis. AnArgentine has reached the final in nine of the 11 editions of the ATP World Tour 250tournament, with champions including Guillermo Coria, Gaston Gaudio, Juan Monaco and DavidNalbandian."
						.getBytes());
		Path path = Paths.get("CopaClaro.txt");
		try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path,
				StandardOpenOption.WRITE)) {
			Future<FileLock> featureLock = asynchronousFileChannel.lock();
			System.out.println("Waiting for the file to be locked ...");
			FileLock lock = featureLock.get();
			// or, use shortcut
			// FileLock lock = asynchronousFileChannel.lock().get();
			if (lock.isValid()) {
				Future<Integer> featureWrite = asynchronousFileChannel.write(buffer, 0);
				System.out.println("Waiting for the bytes to be written ...");
				int written = featureWrite.get();
				// or, use shortcut
				// int written = asynchronousFileChannel.write(buffer,0).get();
				System.out.println("I’ve written " + written + " bytes into " + path.getFileName() + " locked file!");
				lock.release();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
