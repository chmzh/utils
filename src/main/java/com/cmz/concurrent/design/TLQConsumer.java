package com.cmz.concurrent.design;import java.awt.*;

public class TLQConsumer implements Runnable {
  static final long sleepTime = 900;
  protected TwoLockQueue b_; // balking queue class from Chapter 3
  protected TextArea txt_;

  public TLQConsumer(TwoLockQueue b, TextArea txt) {
    b_ = b;
    txt_ = txt;
    //    new Thread(this).start();
  }
  public void run() {
    for (;;) {
      Object x = b_.take();
      if (x != null) // print after each successful take
        txt_.appendText("take" + x + "\n"); 
      Thread.currentThread().yield();
      /*
      try {
        Thread.currentThread().sleep((long)(Math.random() * sleepTime));
      }
      catch (InterruptedException ex) { return; }
      */
    }
  }
}
