package com.cmz.concurrent.design;
public class Point {
  private final int x_,  y_;
  public Point(int x, int y) { x_ = x; y_ = y; }
  public int x() { return x_; }
  public int y() { return y_; }
  public Point duplicate() { return new Point(x_, y_); }
}
