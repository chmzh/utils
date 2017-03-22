package com.cmz.jcsp;

import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;

public class DriverProgram {
	public static void main(String[] args) {
		One2OneChannelInt chan = Channel.one2oneInt();
		new Parallel(new CSProcess[] { 
				new ReadEvenIntsProcess(chan.in()), 
				new SendEvenIntsProcess(chan.out()),

		}).run();
	}
}
