package com.cmz.concurrent.design;public class SimpleTimeOutLock implements Lock {
  public static final long timeOut = 1000; // time-out period

  // Simple key generator
  public static Object newKey() { return new Object(); }

  protected Object key_ = null; // current key

  public boolean hasKey(Object key) { 
    return key == key_;
  } 

  public synchronized void acquire(Object key)
   throws KeyException { // block until released or time-out
    while (key_ != null && key_ != key) { 
      try { wait(timeOut); }
      catch (InterruptedException ex) { 
        throw new KeyException();// fail on interrupt
      }
      if (key_ != null && key_ != key) 
        throw new KeyException();// fail on time-out
    }
    key_ = key;
    notifyAll(); // notify threads waiting to enter on this key
  }

  // wait until key matches current key
  public synchronized void enter(Object key) throws KeyException {
    while (key != key_) {
      try { wait(timeOut); } 
      catch (InterruptedException ex) { 
        throw new KeyException(); 
      }

     if (key_ != key) 
       throw new KeyException();
    }
  }

  // clear key so can accept another
  public synchronized void release(Object key) { 
    if (key_ == key) {
      key_ = null;
      notifyAll(); // notify threads waiting to acquire
    }
  }
}

