package com.cmz.concurrent.design;
public class DiskReadTask extends DiskTask { 
  static final long sleepTime = 10000;

  public DiskReadTask(int cyl, byte[] b, DiskScheduler s) { 
    super(cyl, b, s);
  }

  protected void access() { 
    /* ... read ... */ 
    try {
      Thread.currentThread().sleep((long)(Math.random() * sleepTime));
    }
    catch(InterruptedException ex) {}
  }

}
