package com.cmz.concurrent.design;public class SynchedPoint {
  protected BarePoint delegate_; // fixed, unique

  public SynchedPoint() { delegate_ = new BarePoint(); }

  public synchronized double x() { return delegate_.x; }

  public synchronized double y() { return delegate_.y; }

  public synchronized void setX(double v) { delegate_.x = v; }

  public synchronized void setY(double v) { delegate_.y = v; }

}

