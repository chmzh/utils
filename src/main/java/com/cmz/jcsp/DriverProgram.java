package com.cmz.jcsp;

import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.lang.Parallel;

public class DriverProgram
{
    public static void main(String[] args)
    {
      One2OneChannel chan = Channel.one2one();
      new Parallel
      (
        new CSProcess[]
	    {
	      new SendEvenIntsProcess(chan.out()),
	      new ReadEvenIntsProcess(chan.in())
	    }
      ).run ();
    }
}
