package com.cmz.concurrent.design;import java.awt.*;

public class TLQProducer implements Runnable { // code fragments
  static final long sleepTime = 1000;
  protected TwoLockQueue b_; // balking queue class from Chapter 3
  protected TextArea txt_;
  public TLQProducer(TwoLockQueue b, TextArea txt) { 
    b_ = b; 
    txt_ = txt;
    //    new Thread(this).start(); 
  }
  public void run() {
    int i = 0;
    for (;;) {
      Object x = new Integer(i++); // or whatever
      b_.put(x);
      Thread.currentThread().yield();
      txt_.appendText("put" + x + "\n"); // print after each put
      try {
        Thread.currentThread().sleep((long)(Math.random() * sleepTime));
      }
      catch (InterruptedException ex) { return; }
    }
  }
}
