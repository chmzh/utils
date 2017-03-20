package com.cmz.concurrent.design;import java.util.*;

public abstract class RWVSN {
  protected int activeReaders_ = 0;     // counts
  protected int activeWriters_ = 0;
  protected int waitingReaders_ = 0;
  // the size of the waiting writers vector serves as its count

  // one monitor holds all waiting readers
  protected Object waitingReaderMonitor_ = this;

  // vector of monitors each holding one waiting writer
  protected Vector waitingWriterMonitors_ = new Vector();

  protected abstract void read_(); // implement in subclasses
  protected abstract void write_(); 

  public void read() { beforeRead(); read_();  afterRead(); }
  public void write() { beforeWrite(); write_(); afterWrite(); }

  protected boolean allowReader() { // call under proper synch
    return activeWriters_ == 0 && 
           waitingWriterMonitors_.size() == 0;
  }

  protected boolean allowWriter() { 
    return waitingWriterMonitors_.size() == 0 && 
           activeReaders_ == 0 &&
           activeWriters_ == 0;
  } 

  protected void beforeRead() {
    synchronized(waitingReaderMonitor_) {
      synchronized(this) { // test condition under synch
        if (allowReader()) {
          ++activeReaders_;
          return;
        }
        else
          ++waitingReaders_;
      }
      try { waitingReaderMonitor_.wait(); } 
      catch (InterruptedException ex) {}
    }
  }

  protected void beforeWrite() {
    Object monitor = new Object();
    synchronized (monitor) {
      synchronized(this) {
        if (allowWriter()) {
          ++activeWriters_;
          return;
        }
        waitingWriterMonitors_.addElement(monitor); // append
      }
      try { monitor.wait(); } catch (InterruptedException ex) {}
    }
  }


  protected synchronized void notifyReaders() { // waken readers
    synchronized(waitingReaderMonitor_) { 
      waitingReaderMonitor_.notifyAll();
    }
    activeReaders_ = waitingReaders_; // all waiters now active
    waitingReaders_ = 0;
  }



  protected synchronized void notifyWriter() { // waken 1 writer
    if (waitingWriterMonitors_.size() > 0) {
      Object oldest = waitingWriterMonitors_.firstElement();
      waitingWriterMonitors_.removeElementAt(0);
      synchronized(oldest) { oldest.notify(); }
      ++activeWriters_;
    }
  }

  protected synchronized void afterRead()  { 
    if (--activeReaders_ == 0)
      notifyWriter(); 
  }


  protected synchronized void afterWrite() { 
    --activeWriters_;
    if (waitingReaders_ > 0) // prefer waiting readers
      notifyReaders();
    else
      notifyWriter();
  }
}

