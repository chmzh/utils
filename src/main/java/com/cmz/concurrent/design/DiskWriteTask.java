package com.cmz.concurrent.design;
public class DiskWriteTask extends DiskTask { 
  static final long sleepTime = 10000;

  public DiskWriteTask(int cyl, byte[] b, DiskScheduler s) { 
    super(cyl, b, s);
  }

  protected void access() { /* ... write ... */ 
    /* ... read ... */ 
    try {
      Thread.currentThread().sleep((long)(Math.random() * sleepTime));
    }
    catch(InterruptedException ex) {}
  }
}
