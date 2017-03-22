package com.cmz.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.ChannelOutputInt;

public class SendEvenIntsProcess implements CSProcess 
{
    private ChannelOutputInt out;
    public ChannelOutputInt getOut() {
		return out;
	}
    public SendEvenIntsProcess(ChannelOutputInt out)
    {
      this.out = out;
    }
    public void run()
    {
      for (int i = 2; i <= 100; i = i + 2)
      {
        out.write (new Integer (i));
      }
    }
}
