package com.cmz.concurrent.design;
public class BoundedCounterVSC extends GroundCounter 
                               implements BoundedCounter {
  public BoundedCounterVSC() { super(MIN); }
  public synchronized long value() { return value_(); }

  public synchronized void inc() {
    while (value_() >= MAX)
      try { wait(); } catch(InterruptedException ex) {};
    inc_(); 
    notifyAll(); 
  }

  public synchronized void dec() {
    while (value_() <= MIN)
      try { wait(); } catch(InterruptedException ex) {};
    dec_(); 
    notifyAll();
  }
}
