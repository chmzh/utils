package com.cmz.concurrent.design;
public class BareCounter { 
  private long count_;
  public long value()        { return count_; }
  public void add(long c)    { count_ += c; }
  public BareCounter(long c) { count_ = c; }
}
