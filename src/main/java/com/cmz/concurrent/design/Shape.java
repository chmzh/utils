package com.cmz.concurrent.design;public class Shape {
  protected double x_= 0.0, y_=0.0, width_=0.0, height_=0.0;

  public synchronized double x() { return x_; }
  public synchronized double y() { return y_; }

  public synchronized double width() { return width_; }

  public synchronized double height() { return height_; }

  public synchronized void adjustLocation() {
    x_ = longCalculation1(); 
    y_ = longCalculation2();
  }

  public synchronized void adjustDimensions() {
    width_ = longCalculation3(); 
    height_ = longCalculation4();
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
