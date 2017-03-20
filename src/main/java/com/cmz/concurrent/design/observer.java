package com.cmz.concurrent.design;
public class observer {
  protected double cachedState_;
  protected Subject subj_;

  public observer(Subject s) {
    subj_ = s;
    cachedState_ = s.currentValue();
    display(); 
  }

  public synchronized void changeNotification(Subject s) {
    if (s != subj_) return; // only one subject supported
    double oldState = cachedState_;
    cachedState_ = subj_.currentValue();     // probe
    if (oldState != cachedState_) display(); // redisplay
  }

  protected synchronized void display() { // default version
    System.out.println(cachedState_);
  }
}

