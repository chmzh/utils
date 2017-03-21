package com.cmz.concurrent.design.partner;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class IOEventTask implements Runnable { // generic code sketch

	static final int BUFFSIZE = 1024;

	protected final Socket socket;
	protected final InputStream input;
	protected volatile boolean done = false; // latches true

	IOEventTask(Socket s) throws IOException {
		socket = s;
		input = socket.getInputStream();
	}

	void processCommand(byte[] b, int n) {
	}

	void cleanup() {
	}

	public void run() { // trigger only when input available
		if (done)
			return;

		byte[] commandBuffer = new byte[BUFFSIZE];
		try {
			int bytes = input.read(commandBuffer, 0, BUFFSIZE);
			if (bytes != BUFFSIZE)
				done = true;
			else
				processCommand(commandBuffer, bytes);
		} catch (IOException ex) {
			cleanup();
			done = true;
		} finally {
			if (!done)
				return;
			try {
				input.close();
				socket.close();
			} catch (IOException ignore) {
			}
		}
	}

	// Accessor methods needed by triggering agent:
	boolean done() {
		return done;
	}

	InputStream input() {
		return input;
	}
}
