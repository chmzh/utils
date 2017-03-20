package com.cmz.concurrent.design;
public class NotifyingLong {
  private long value_;
  private Object observer_;

  public NotifyingLong(Object o, long v) {
    observer_ = o; 
    value_ = v; 
  }

  public synchronized long value() { return value_; }

  public void setValue(long v) {
    synchronized(this) { value_ = v; }
    synchronized(observer_) { observer_.notifyAll(); }
  }
}

