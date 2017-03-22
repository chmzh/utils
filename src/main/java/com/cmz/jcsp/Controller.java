package com.cmz.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;

public class Controller implements CSProcess
{
  private long interval;
  private final ChannelOutputInt suspend, inject;
  private final ChannelInputInt factor;
  public Controller (long interval, ChannelOutputInt suspend, ChannelOutputInt inject, 
    ChannelInputInt factor)
  { 
    this.interval = interval;
    this.suspend = suspend;
    this.inject = inject;
    this.factor = factor;
  }
  public void run ()
  {
	int currFactor = 0;
	final CSTimer tim = new CSTimer ();
	long timeout = tim.read ();
	while (true)
	{
	  timeout += interval;
	  tim.after (timeout);        // blocks until timeout reached
	  suspend.write (0);          // suspend signal (value irrelevant)
	  currFactor = factor.read ();			
	  currFactor ++;              // compute new factor
	  inject.write (currFactor);  // inject new factor
	}
  }
}
