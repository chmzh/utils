package com.cmz.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.plugNplay.ints.FixedDelayInt;
import org.jcsp.plugNplay.ints.NumbersInt;
import org.jcsp.plugNplay.ints.PrinterInt;

public class DriverProgram1 {
	 public static void main(String args[])
	  {
		try
		{
		  final One2OneChannelInt temp = Channel.one2oneInt();
		  final One2OneChannelInt in = Channel.one2oneInt();
		  final One2OneChannelInt suspend = Channel.one2oneInt();
		  final One2OneChannelInt factor = Channel.one2oneInt();
		  final One2OneChannelInt inject = Channel.one2oneInt();
		  final One2OneChannelInt out = Channel.one2oneInt();
			
		  new Parallel
		  (
			new CSProcess[]
			{
			  new NumbersInt (temp.out()),
			  new FixedDelayInt (1000, temp.in(), in.out()),
			  new ScaleInt (2, suspend.in(), in.in(), factor.out(), inject.in(), out.out()),
			  new Controller (6000, suspend.out(), inject.out(), factor.in()),
			  new PrinterInt (out.in(), "--> ", "\n")
			}
		  ).run ();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	  }
}
