package com.cmz.concurrent.design;
import java.util.*;

public class MTSubject extends Subject {
  public MTSubject(double initstate) { super(initstate); }

  public synchronized void changeValue(double newstate) {
    val_ = newstate;
    for (Enumeration e = obs_.elements(); e.hasMoreElements();){
      observer o = (observer)(e.nextElement());
      new Thread(new MTSNotifier(this, o)).start();
    }
  }
}

class MTSNotifier implements Runnable {// the waiter class 
  private Subject  sub_;
  private observer obs_;
  MTSNotifier(Subject s, observer o) { sub_ = s; obs_ = o; }
  public void run() { obs_.changeNotification(sub_); }
}

