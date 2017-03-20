package com.cmz.concurrent.design;
public class DiskScheduler { // generic one-at-a-time version
  protected DiskTaskQueue queue_ = new DiskTaskQueue();     // fixed, unique
  protected DiskTask running_ = null; // currently running task

  public void enter(DiskTask t) {
    synchronized (t) {        // lock t in order to use wait below
      for (;;) {
        synchronized(this) {
          if (t == running_) 
            return;
          else if (running_ == null) {
            running_ = t;
            return;
          }
          else
            queue_.put(t);  // will be dequeued by done method
        }
         try {  t.wait(); } catch (InterruptedException ex) {}
      }
    }
  }

  public void done(DiskTask t) {
    synchronized(this) {
      if (t != running_) 
        return;
      running_ = queue_.take();
      if (running_ == null) 
        return;
    }
    synchronized(running_) {
      running_.notifyAll();
    }
  }
}

