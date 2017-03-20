package com.cmz.concurrent.design;public abstract class RW {
  protected int activeReaders_ = 0;  // threads executing read_
  protected int activeWriters_ = 0;  // always zero or one
  protected int waitingReaders_ = 0; // threads not yet in read_
  protected int waitingWriters_ = 0; // same for write_

  protected abstract void read_(); // implement in subclasses
  protected abstract void write_(); 

  public void read()  {
    beforeRead(); 
    read_(); 
    afterRead();
  }

  public void write() { 
    beforeWrite(); 
    write_();
    afterWrite();
  }

  protected boolean allowReader() {
    return waitingWriters_ == 0 && activeWriters_ == 0;
  }

  protected boolean allowWriter() {
    return activeReaders_ == 0 && activeWriters_ == 0;
  }

  protected synchronized void beforeRead() {
    ++waitingReaders_;
    while (!allowReader())
      try { wait(); } catch (InterruptedException ex) {}
    --waitingReaders_;
    ++activeReaders_;
  }

  protected synchronized void afterRead()  { 
    --activeReaders_;
    notifyAll();
  }

  protected synchronized void beforeWrite() {
    ++waitingWriters_;
    while (!allowWriter()) 
      try { wait(); } catch (InterruptedException ex) {}
    --waitingWriters_;
    ++activeWriters_;
  }

  protected synchronized void afterWrite() { 
    --activeWriters_;
    notifyAll();
  }

}

