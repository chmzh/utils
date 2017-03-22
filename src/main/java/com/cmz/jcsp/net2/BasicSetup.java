package com.cmz.jcsp.net2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jcsp.net2.NetChannelInput;
import org.jcsp.net2.NetChannelOutput;
import org.jcsp.net2.Node;
import org.jcsp.net2.cns.CNS;
import org.jcsp.net2.tcpip.TCPIPNodeFactory;

public class BasicSetup {
	public static void main(String[] args) {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter IP of CNS: ");
		String cnsIP = "";
		try {
			cnsIP = stdIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Connect directly to CNS. Listening port not known
		 Node.getInstance().init(new TCPIPNodeFactory(cnsIP)); // Must use CNS
		// to create channels
		NetChannelInput in = CNS.net2one("chanIn");
		NetChannelOutput out = CNS.one2net("chanOut");
	}
}
