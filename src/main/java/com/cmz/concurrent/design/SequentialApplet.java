package com.cmz.concurrent.design;import java.awt.*;
import java.applet.*;

public class SequentialApplet extends Applet {
  protected TextArea txt_;
  protected SimpleMessagePrinter hello_;
  protected SimpleMessagePrinter goodbye_;

  public SequentialApplet() {
    txt_ = new TextArea(4, 40); // 4 rows, 40 columns
    hello_ = new SimpleMessagePrinter("Hello\n", txt_);
    goodbye_ = new SimpleMessagePrinter("Goodbye\n", txt_);
  }

  public void init() {
    add(txt_); // add text area to applet display
  }

  public void start() { 
    hello_.run(); 
    goodbye_.run();
  }
}
