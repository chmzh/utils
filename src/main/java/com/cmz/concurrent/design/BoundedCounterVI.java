package com.cmz.concurrent.design;
public class BoundedCounterVI implements BoundedCounter {
  protected long count_ = MIN;

  public synchronized long value() { return count_; }

  public synchronized void inc()  {
    while (count_ == MAX)
      try { wait(); } catch(InterruptedException ex) {};
    if (count_++ == MIN)
      notifyAll(); // signal if was bottom
  }

  public synchronized void dec()  {
    while (count_ == MIN)
      try { wait(); } catch(InterruptedException ex) {};
    if (count_-- == MAX) 
      notifyAll(); // signal if was top
  }
}
