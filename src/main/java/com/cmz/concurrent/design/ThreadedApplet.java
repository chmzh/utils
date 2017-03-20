package com.cmz.concurrent.design;import java.awt.*;
import java.applet.*;

public class ThreadedApplet extends SequentialApplet {
  public void start() {
    new Thread(hello_).start();
    new Thread(goodbye_).start();
  }
}
