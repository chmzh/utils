package com.cmz.concurrent.design;
public class LinkedCell {
  protected double value_;
  protected LinkedCell next_; // fixed

  public LinkedCell (double v, LinkedCell t) { 
    value_ = v; next_ = t; 
  }

  public synchronized double value() { return value_; }

  public synchronized void setValue(double v) { value_ = v; }

  public LinkedCell next() { return next_; } // no synch needed

  public double sum() { // add up all element values
    double v = value(); // get value via synchronized accessor 
    if (next() != null) 
      v += next().sum();
    return v;
  }

  public boolean includes(double x) { // search for x
    synchronized(this) {   //  synch to access value
      if (value_ == x) return true;
    }
    if (next() == null) return false;
    else return next().includes(x);
  }
}
