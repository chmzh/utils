package com.cmz.concurrent.design;
public class BoundedBufferVTO extends BoundedBufferVST {

  public BoundedBufferVTO(int capacity) 
   throws IllegalArgumentException {
     super(capacity);
  }

  // returns null on time-out or interrupt
  public synchronized Object take(long maxWaitMillis) {
    if (usedSlots_ == 0) {
      long waitTime = maxWaitMillis;
      long startTime = System.currentTimeMillis();
      for (;;) {
        try { 
          wait(waitTime);
        } 
        catch(InterruptedException ex) {
          return null;
        } 

        if (usedSlots_ > 0) // recheck condition
          break;
        else { 
          long now = System.currentTimeMillis();
          long timeSoFar = now - startTime;
          if (timeSoFar >= maxWaitMillis)
            return null; 
          else // adjust time-out for next time
            waitTime = maxWaitMillis - timeSoFar;
        }
      }
    }

    // otherwise, same code as guarded version
    Object x = array_[takePtr_];
    array_[takePtr_] = null;
    takePtr_ = (takePtr_ + 1) % array_.length;

    if (usedSlots_-- == array_.length)
      notifyAll();
    return x;
  }

}
