package com.cmz.concurrent.design;import java.util.*;

public class Subject {
  protected double val_;   // modeled state
  protected Vector obs_;   // observers 
  public Subject(double initstate) {
    val_ = initstate;
    obs_ = new Vector();
  }

  public synchronized void attach(observer o) { 
    obs_.addElement(o);
  }
  public synchronized void detach(observer o) { 
    obs_.removeElement(o);
  }

  public synchronized double currentValue() { return val_; }

  // Changing state generates notifications to each observer 
  public synchronized void changeValue(double v) {
    val_ = v;
    for (Enumeration e = obs_.elements(); e.hasMoreElements();){
      observer o = (observer)(e.nextElement());
      o.changeNotification(this);
    }
  }
}

