package com.cmz.concurrent.design;
public class Dot {
  protected Point loc_;
  public Dot(int x, int y) { loc_ = new Point(x, y); }
  public Point location() { return loc_; }

  // all location assignments go through updateLoc
  protected synchronized void updateLoc(Point newLoc) { 
    loc_ = newLoc; 
  }

  public void moveTo(int x, int y) { 
    updateLoc(new Point(x, y)); 
  }

  public void shiftX(int deltaX) {
    Point currentLoc = location();
    updateLoc(new Point(currentLoc.x() + deltaX,
                        currentLoc.y()));
  }
}
