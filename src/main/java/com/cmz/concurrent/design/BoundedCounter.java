package com.cmz.concurrent.design;
public interface BoundedCounter {
  public static final long MIN = 0;  // minimum allowed value
  public static final long MAX = 5; // maximum allowed value

  public long value(); // invariant: MIN <= value() <= MAX
                       // initial condition: value() == MIN
  public void inc();   // increment only when value() < MAX
  public void dec();   // decrement only when value() > MIN
}

