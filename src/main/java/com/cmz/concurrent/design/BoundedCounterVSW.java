package com.cmz.concurrent.design;
public class BoundedCounterVSW implements BoundedCounter {
  static final int BOTTOM = 0;
  static final int MIDDLE = 1;
  static final int TOP    = 2;

  protected int state_ = BOTTOM;  // the state variable
  protected long count_ = MIN;

  public synchronized long value() { return count_; }

  public synchronized void inc()  { 
    while (state_ == TOP) 
      try { wait(); } catch(InterruptedException ex) {};
    ++count_;
    checkState();
  }

  public synchronized void dec()  {
    while (state_ == BOTTOM) 
      try { wait();} catch(InterruptedException ex) {};
    --count_; 
    checkState(); 
  }

  protected synchronized void checkState() {
    int oldState = state_;
    if      (count_ == MIN) state_ = BOTTOM;
    else if (count_ == MAX) state_ = TOP;
    else                    state_ = MIDDLE;

    if (state_ != oldState &&
        (oldState == TOP || oldState == BOTTOM))
       notifyAll();
  }
}

