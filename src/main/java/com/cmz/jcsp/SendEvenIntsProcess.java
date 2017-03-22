package com.cmz.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

public class SendEvenIntsProcess implements CSProcess 
{
    private ChannelOutput out;
    public SendEvenIntsProcess(ChannelOutput out)
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
