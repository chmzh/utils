package com.cmz.concurrent.design;
public class BoundedCounterVD implements BoundedCounter {
  private BareCounter delegate_; // fixed, unique 
  public BoundedCounterVD() { 
    delegate_ = new BareCounter(MIN);
  }

  public synchronized long value() {return delegate_.value(); }
 
  public synchronized void inc()   {
    while (delegate_.value() >= MAX) 
      try { wait(); } catch(InterruptedException ex) {};
    delegate_.add(1);
    notifyAll();
  }

  public synchronized void dec()   {
    while (delegate_.value() <= MIN)
      try { wait(); } catch(InterruptedException ex) {};
    delegate_.add(-1);
    notifyAll();
  }

}

