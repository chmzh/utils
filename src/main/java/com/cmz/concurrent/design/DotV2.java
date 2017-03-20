package com.cmz.concurrent.design;
public class DotV2 {
  protected Point loc_;

  public DotV2(int x, int y) { loc_ = new Point(x, y); }

  public Point location() { return loc_; }

  // the atomic update method
  protected synchronized boolean commit(Point assumed, 
                                        Point next) {
    boolean success = (assumed == loc_);
    if (success)  loc_ = next;
    return success;
  }

  // Exception-based
  public void shiftXY(int deltaX, int deltaY) 
   throws InterferenceException {
    Point assumed = location();
    Point next = new Point(assumed.x() + deltaX, 
                           assumed.y() + deltaY);
    if (!commit(assumed, next)) 
      throw new InterferenceException();
  }

  // automatic retry
  public void shiftX(int deltaX) {
    Point assumed = null;
    Point next = null;
    do {
      assumed = location();
      next = new Point(assumed.x() + deltaX, assumed.y());
    } while (!commit(assumed, next));
  }

  // partially synchronized
  public Point shiftY(int deltaY) {
    synchronized(this) {
      Point assumed = location();
      Point next = new Point(assumed.x(), assumed.y() + deltaY);
      commit(assumed, next);
    }
    return location();
  }

  // fully synchronized 
  public synchronized void move(int x, int y) {
    Point assumed = location();
    Point next = new Point(x, y);
    commit(assumed, next); // no need to check failure
  }

}

