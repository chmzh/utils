package com.cmz.concurrent.design;import java.awt.*;

public class SimpleMessagePrinter implements Runnable {
  protected String msg_;    // The message to print
  protected TextArea txt_;  // The place to print it

  public SimpleMessagePrinter(String m, TextArea txt) {
    msg_ = m; 
    txt_ = txt;
  }

  public void run() { 
    txt_.appendText(msg_); // display the message 
  }
}


