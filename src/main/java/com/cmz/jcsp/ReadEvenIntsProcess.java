package com.cmz.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;

public class ReadEvenIntsProcess implements CSProcess
{
    private ChannelInput in;
    public ReadEvenIntsProcess(ChannelInput in)
    {
      this.in = in;
    }
    public void run()
    {
      while (true)
      {
        Integer d = (Integer)in.read();
        System.out.println("Read: " + d.intValue());
      }
    }
}
