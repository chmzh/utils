package com.cmz.jcsp.net2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jcsp.net2.NetChannelInput;
import org.jcsp.net2.NetChannelOutput;
import org.jcsp.net2.Node;
import org.jcsp.net2.bns.BNS;
import org.jcsp.net2.cns.CNS;
import org.jcsp.net2.tcpip.TCPIPNodeAddress;
import org.jcsp.net2.tcpip.TCPIPNodeFactory;

public class BasicSetup2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		//System.out.print("Enter port to listen on: ");
		int port = 8000;//Integer.parseInt(stdIn.readLine()); // Create a node address
														// to listen on.
		// Use default IP, which is the most accessible
		TCPIPNodeAddress addr = new TCPIPNodeAddress(port); // Initialise Node
		Node.getInstance().init(addr);
		// Get IP of NodeServer
		System.out.print("IP of NodeServer: ");
		String nsIP = "127.0.0.1";//stdIn.readLine();
		// Create address to NodeServer. Default port is 7890
		TCPIPNodeAddress nsAddr = new TCPIPNodeAddress(nsIP, 8081); // Initialise
																	// CNS and
																	// BNS
		CNS.initialise(nsAddr);
		BNS.initialise(nsAddr);
	}
}
