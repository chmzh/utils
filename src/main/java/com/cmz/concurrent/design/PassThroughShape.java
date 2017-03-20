package com.cmz.concurrent.design;
public class PassThroughShape {
  protected AdjustableLocation loc_; // fixed, unique
  protected AdjustableDimension dim_; // fixed, unique

  public PassThroughShape() { 
    loc_ = new AdjustableLocation(0, 0);
    dim_ = new AdjustableDimension(0, 0);
  }

  public double x() { return loc_.x(); }
  public double y() { return loc_.y(); }
  public double width() { return dim_.width(); }
  public double height() { return dim_.height(); } 
  public void adjustLocation() { loc_.adjust(); }
  public void adjustDimensions() { dim_.adjust(); } 
}


// Non-public for convenience

/* public */ class AdjustableLocation {
  protected double x_,  y_;
  public AdjustableLocation(double x, double y) { 
    x_ = x; y_ = y; 
  }
  public synchronized double x() { return x_; }
  public synchronized double y() { return y_; }
  public synchronized void adjust() {
    x_ = longCalculation1(); 
    y_ = longCalculation2();
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
}

/* public */ class AdjustableDimension { 
  protected double width_=0.0, height_=0.0;

  public AdjustableDimension(double h, double w) { 
    height_ = h; width_ = w; 
  }

  public synchronized double width() { return width_; }

  public synchronized double height() { return height_; }

  public synchronized void adjust() {
    width_ = longCalculation3(); 
    height_ = longCalculation4();
  }

  // stand-ins for the `long calculations'
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

