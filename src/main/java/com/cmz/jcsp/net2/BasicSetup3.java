package com.cmz.jcsp.net2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jcsp.lang.ProcessManager;
import org.jcsp.net2.Link;
import org.jcsp.net2.Node;
import org.jcsp.net2.tcpip.TCPIPLink;
import org.jcsp.net2.tcpip.TCPIPNodeAddress;

public class BasicSetup3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// Set Link properties
		Link.LINK_PRIORITY = ProcessManager.PRIORITY_MAX;
		TCPIPLink.BUFFER_SIZE = 1500;
		TCPIPLink.NAGLE = true;
		//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		//System.out.print("Enter port to listen on: ");
		int port = 8000;//Integer.parseInt(stdIn.readLine());
		// Create a node address to listen on.
		// Use default IP, which is the most accessible
		TCPIPNodeAddress addr = new TCPIPNodeAddress(port);
		// Initialise Node
		Node.getInstance().init(addr);
	}
}
