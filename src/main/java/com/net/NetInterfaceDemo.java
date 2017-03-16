package com.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class NetInterfaceDemo {
	public static void main(String args[]) throws SocketException {
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets)) {
			System.out.println("Display name:" + netint.getDisplayName());
			System.out.println("Name:" + netint.getName());
			System.out.println("MTU:" + netint.getMTU());
			System.out.println("Interface is up:" + netint.isUp());
			Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
			for (InetAddress inetAddress : Collections.list(inetAddresses)) {
				System.out.println("InetAddress:" + inetAddress);
			}
			System.out.println();
		}
	}
}
