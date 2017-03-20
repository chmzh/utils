package com.cmz.concurrent.design;
abstract public class DiskTask implements Runnable {
  protected int cyl_;              // fixed; cylinder to access
  protected DiskScheduler scheduler_;  // the scheduler
  protected Thread me_;            // thread running this task
  protected byte[] buff_;          // buffer to read/write

  public DiskTask(int cyl, byte[] b, DiskScheduler s) { 
    cyl_ = cyl; 
    buff_ = b;
    scheduler_ = s;
    me_ = Thread.currentThread();
  }

  public int cylinder() { return cyl_; } // for use in ordering
  public Thread thread() { return me_; }

  protected abstract void access(); // specialize in subclasses
 
  public void run() {
    scheduler_.enter(this);
    access();
    scheduler_.done(this);
  }
}

