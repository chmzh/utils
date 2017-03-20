package com.cmz.concurrent.design;
import java.awt.*;

public class BasicBox extends Box {
  protected Dimension size_;
  public BasicBox(int xdim, int ydim) {
    super();
    size_ = new Dimension(xdim, ydim);
  }

  public Dimension size() { return size_; }

  public void show(Graphics g, java.awt.Point origin) {
    g.setColor(color_);
    g.fillRect(origin.x, origin.y, size_.width, size_.height);
  }

  public Box duplicate() {
    Box p =  new BasicBox(size_.width, size_.height);
    p.color(color_);
    return p;
  }
}

