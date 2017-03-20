package com.cmz.concurrent.design;
public class BoundedCounterVNL implements BoundedCounter {
  private NotifyingLong c_ = new NotifyingLong(this, MIN);

  public synchronized long value() { return c_.value(); }

  public synchronized void inc()  { 
    while (c_.value() >= MAX)
      try { wait(); } catch(InterruptedException ex) {};
    c_.setValue(c_.value()+1);
  }

  public synchronized void dec()  {
    while (c_.value() <= MIN)
      try { wait(); } catch(InterruptedException ex) {};
    c_.setValue(c_.value()-1);
  }
}
