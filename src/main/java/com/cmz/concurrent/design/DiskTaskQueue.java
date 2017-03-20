package com.cmz.concurrent.design;
import java.util.*;

public class DiskTaskQueue {
  protected Vector currentScan_ = new Vector();
  protected Vector nextScan_ = new Vector();
  protected int currentCyl_ = 0;

  public synchronized boolean isEmpty() {
    return currentScan_.size() == 0 && nextScan_.size() == 0;
  }

  public synchronized void put(DiskTask t) {
    if (t.cylinder() >= currentCyl_) 
      add(t, currentScan_);
    else
      add(t, nextScan_);
  }

  public synchronized DiskTask take() { // return null if empty
    if (currentScan_.size() == 0) {
      Vector tmp = currentScan_;
      currentScan_ = nextScan_;
      nextScan_ = tmp;
    }
    if (currentScan_.size() == 0) 
      return null;
    DiskTask t = (DiskTask)(currentScan_.firstElement());
    currentScan_.removeElementAt(0);
    currentCyl_ = t.cylinder();
    return t;
  }
    
  protected void add(DiskTask t, Vector v) {
    for (int i = 0; i < v.size(); ++i) {
      DiskTask d = (DiskTask)(v.elementAt(i));
      if (t.cylinder() < d.cylinder()) {
        v.insertElementAt(t, i);
        return;
      }
    }
    v.addElement(t);
  }

}
