package com.cmz.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelInputInt;

public class ReadEvenIntsProcess implements CSProcess
{
    private ChannelInputInt in;
    public ChannelInputInt getIn() {
		return in;
	}
    public ReadEvenIntsProcess(ChannelInputInt in)
    {
      this.in = in;
    }
    public void run()
    {
      while (true)
      {
        Integer d = in.read();
        System.out.println("Read: " + d.intValue());
      }
    }
}
