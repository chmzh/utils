package com.cmz.concurrent.design;

import java.awt.*;
import java.applet.*;

public class CounterAppletV1 extends Applet {
  static final int nThreads = 5;

  protected BoundedCounter raw_;
  protected LabelledCounter counter_;
  protected CounterRunner[] ups_;
  protected CounterRunner[] downs_;
  protected Thread[] ut_;
  protected Thread[] dt_;
  protected Button startstop_ = new Button("start/stop");
  protected boolean started_ = false;

  public CounterAppletV1() {
    raw_ = new BoundedCounterV1();
  }

  public void init() {
    setLayout(new BorderLayout());
    counter_ = new LabelledCounter(raw_);
    Panel p1 = new Panel();
    ups_ = new CounterRunner[nThreads];
    ut_ = new Thread[nThreads];
    for (int i = 0; i < nThreads; ++i) {
      ups_[i] = new CounterRunner(counter_, true);
      p1.add(ups_[i]);
    }
    add("North", p1);
    Panel p2 = new Panel();
    downs_ = new CounterRunner[nThreads];
    dt_ = new Thread[nThreads];
    for (int i = 0; i < nThreads; ++i) {
      downs_[i] = new CounterRunner(counter_, false);
      p2.add(downs_[i]);
    }
    add("South", p2);
    add("West", counter_);
    startstop_.setBackground(Color.red);
    add("East", startstop_);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target == startstop_) {
      if (!started_) {
        startstop_.setBackground(Color.green);
        started_ = true;
        for (int i = 0; i < nThreads; ++i) {
          ut_[i] = new Thread(ups_[i]);
          dt_[i] = new Thread(downs_[i]);
          ut_[i].start();
          dt_[i].start();
        }
      }
      else {
        startstop_.setBackground(Color.red);
        started_ = false;
        for (int i = 0; i < nThreads; ++i) {
          ut_[i].stop();
          dt_[i].stop();
        }

      }
      return true;
    }
    return false;
  }


}

class CounterRunner extends java.awt.Label implements Runnable {
  static final long sleepTime = 10000;
  static final Color idleColor = Color.lightGray;
  static final Color incColor = Color.red;
  static final Color decColor = Color.blue;
  protected  BoundedCounter c_;
  protected boolean up_;
  protected int seq_ = 0;
  CounterRunner(BoundedCounter c, boolean up) { 
    super("      0");
    up_ = up; c_ = c; 
    setBackground(idleColor);
  }

  public void run() {
    for (;;) {
      setBackground((up_)? incColor : decColor); 
      if (up_) c_.inc(); else c_.dec();
      ++seq_;
      setText(" " + seq_);
      try {
        setBackground(idleColor);
        Thread.currentThread().sleep((long)(Math.random() * sleepTime));
      }
      catch(InterruptedException ex) {}
    }
  }
}

class LabelledCounter extends java.awt.Label implements BoundedCounter {
  protected BoundedCounter delegate_;

  public LabelledCounter(BoundedCounter delegate) {
    super("   " + delegate.value());
    delegate_ = delegate;
  }

  public void inc() {
    delegate_.inc();
    setText(" " + delegate_.value());
  }

  public void dec() {
    delegate_.dec();
    setText(" " + delegate_.value());
  }

  public long value() { return delegate_.value(); }
}
