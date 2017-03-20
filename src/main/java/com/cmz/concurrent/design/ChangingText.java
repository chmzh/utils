package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class ChangingText implements AutonomousGraphic {
  private String msg_;             // message to display
  private Rectangle drawingArea_;  // where to display it
  private Color color_;            // current color
  private Applet app_;             // for loopback

  public ChangingText(Applet app, Rectangle area, String msg) {
    app_ = app; 
    msg_ = msg; 
    drawingArea_ = area;
    color_ = Color.black;
  }

  protected synchronized void changeColor() {
    color_ = new Color((int)(Math.random() * 255.0),
                       (int)(Math.random() * 255.0),
                       (int)(Math.random() * 255.0));
  }

  public void run() {
    for (;;) {
      changeColor();
      app_.repaint(); // request a repaint from applet
      // do nothing for a while
      try { Thread.currentThread().sleep(5000);  }
      catch(InterruptedException ex) { return; }
    }
  }

  public void paint(Graphics g) {
    Color c;
    synchronized(this) { c = color_; }
    g.setColor(c);
    // code to clip and center to fit nicely in rectangle ...
    g.drawString(msg_, drawingArea_.x, drawingArea_.y);
  }

}
