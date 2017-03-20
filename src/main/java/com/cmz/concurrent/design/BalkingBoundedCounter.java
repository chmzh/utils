package com.cmz.concurrent.design;
public class BalkingBoundedCounter {
  protected long count_ = BoundedCounter.MIN;

  public synchronized long value() { return count_; }

  public synchronized void inc() throws CannotIncrementException {
   if (count_ >= BoundedCounter.MAX) 
     throw new CannotIncrementException();
   else 
     ++count_;
  }

  public synchronized void dec()  throws CannotDecrementException {
   if (count_ <= BoundedCounter.MIN) 
     throw new CannotDecrementException();
   else 
     --count_;
  }
}
