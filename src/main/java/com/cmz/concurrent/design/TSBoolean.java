package com.cmz.concurrent.design;public class TSBoolean {
  private boolean value_ = false;

  // set to true; return old value
  public synchronized boolean testAndSet() {
    boolean oldValue = value_;
    value_ = true;
    return oldValue;
  }

  public synchronized void clear() { value_ = false; }
}

