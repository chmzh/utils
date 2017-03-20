package com.cmz.concurrent.design;import java.awt.*;

public class WrappedBox extends Box {

  protected Dimension wrapperSize_;
  protected Box inner_;

  public WrappedBox(Box inner, Dimension wrapperSize) {
    inner_ = inner;
    wrapperSize_ = wrapperSize;
  }

  public Dimension size() { 
    Dimension is = inner_.size();
    return new Dimension(is.width + wrapperSize_.width + 1,
                         is.height + wrapperSize_.height + 1);
  }

  
  public void show(Graphics g, java.awt.Point origin) {
    g.setColor(color_);
    Dimension sz = size();
    g.fillRect(origin.x, origin.y, sz.width, sz.height);
    inner_.show(g, new java.awt.Point(origin.x + wrapperSize_.width / 2,
                             origin.y + wrapperSize_.height / 2));
  }

  public Box duplicate() { 
    WrappedBox p = new WrappedBox(inner_.duplicate(), wrapperSize_);
    p.color(color_);
    return p;
  }

}
