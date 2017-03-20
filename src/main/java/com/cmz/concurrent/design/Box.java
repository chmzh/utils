package com.cmz.concurrent.design;
import java.awt.*;

public abstract class Box {
  protected Color color_ = Color.white; 
  public synchronized Color color() { return color_; }
  public synchronized void  color(Color c) { color_ = c; }

  public abstract Dimension size();
  public abstract Box duplicate();
  public abstract void show(Graphics g, java.awt.Point origin);
}

