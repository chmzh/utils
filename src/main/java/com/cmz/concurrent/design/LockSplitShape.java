package com.cmz.concurrent.design;
public class LockSplitShape {
  protected double x_= 0.0, y_=0.0, width_=0.0, height_=0.0;
  protected Object locationLock_ = new Object(); 
  protected Object dimensionLock_ = new Object();

  public double x() { 
    synchronized(locationLock_) { return x_; }
  }

  public double y() { 
    synchronized(locationLock_) { return y_; }
  }

  public void adjustLocation() {
    synchronized(locationLock_) {
      x_ = longCalculation1(); 
      y_ = longCalculation2();
    }
  }

  public double height() { 
    synchronized(locationLock_) { return height_; }
  }

  public double width() { 
    synchronized(locationLock_) { return width_; }
  }

  public void adjustDimension() {
    synchronized(dimensionLock_) {
      height_ = longCalculation3(); 
      width_ = longCalculation4();
    }
  }


  // stand-ins for the `long calculations'
  double longCalculation1() { 
    try { Thread.currentThread().sleep(1000); }
    catch (InterruptedException ex) {}
    return 1.0;
  }

  double longCalculation2() { 
    try { Thread.currentThread().sleep(1000); }
    catch (InterruptedException ex) {}
    return 2.0;
  }

  double longCalculation3() { 
    try { Thread.currentThread().sleep(1000); }
    catch (InterruptedException ex) {}
    return 3.0;
  }

  double longCalculation4() { 
    try { Thread.currentThread().sleep(1000); }
    catch (InterruptedException ex) {}
    return 4.0;
  }
}
