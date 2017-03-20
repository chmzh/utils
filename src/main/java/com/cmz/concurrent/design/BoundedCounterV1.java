package com.cmz.concurrent.design;
public class BoundedCounterV1 implements BoundedCounter {
  protected long count_ = MIN;

  public synchronized long value() { return count_; }

  public synchronized void inc()  {
    awaitIncrementable(); 
    setCount(count_ + 1);
  }

  public synchronized void dec()  {
    awaitDecrementable();
    setCount(count_ - 1);
  }
  protected synchronized void setCount(long newValue) {
    count_ = newValue;
    notifyAll(); // wake up any thread depending on new value
  }
  protected synchronized void awaitIncrementable() {
    while (count_ >= MAX) 
      try { wait(); } catch(InterruptedException ex) {};
  }

  protected synchronized void awaitDecrementable() {
    while (count_ <= MIN)
      try { wait(); } catch(InterruptedException ex) {};
  }
}

