package com.cmz.concurrent.design;

import java.awt.*;
import java.applet.*;

public class AlternatingMessagePrinter 
             extends SimpleMessagePrinter {

  protected AlternatingMessagePrinter next_; // the other object
  protected AlternatingMessagePrinter turn_; // go if turn==this

  public AlternatingMessagePrinter(String m, TextArea txt) {
    super(m, txt); 
    turn_ = null; // it's nobody's turn until told otherwise
    next_ = null; // don't know yet who next is
  }

  // called by applet to establish other object
  public synchronized void setNext(AlternatingMessagePrinter p){
    next_ = p;
  }

  // called by another object telling you that it is your turn
  public synchronized void turn() { 
    turn_ = this;
    notifyAll(); // signal thread waiting in run
  }

  public synchronized void run() {
    for (;;) {
      while (turn_ != this) {   // wait until turn
        try { wait(); }         // woken up from turn method
        catch (InterruptedException ex) { return; }
      }

      txt_.appendText(msg_);    // take turn

      // alert next 
      turn_ = null;
      next_.turn();

      try {                     // do nothing for a while
        long delay = (long)(Math.random() * 10000.0);
        Thread.currentThread().sleep(delay);
      }
      catch (InterruptedException e) { return; }
    }

  }
}
