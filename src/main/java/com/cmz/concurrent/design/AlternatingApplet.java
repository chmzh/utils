package com.cmz.concurrent.design;

import java.awt.*;
import java.applet.*;

public class AlternatingApplet extends ThreadedApplet {

  public AlternatingApplet() {
    txt_ = new TextArea(4, 40);
    AlternatingMessagePrinter h = 
       new AlternatingMessagePrinter("Hello\n", txt_);
    AlternatingMessagePrinter g = 
       new AlternatingMessagePrinter("Goodbye\n", txt_);

    // initialize inherited instance variables
    hello_ = h;
    goodbye_ = g;

 
    // initialize links and turn 
    h.setNext(g);
    g.setNext(h);
    h.turn();
  }

}
