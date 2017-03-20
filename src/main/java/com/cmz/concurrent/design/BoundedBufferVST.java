package com.cmz.concurrent.design;
public class BoundedBufferVST implements BoundedBuffer {
  protected Object[]  array_;      // the elements
  protected int putPtr_ = 0;       // circular indices
  protected int takePtr_ = 0;     
  protected int usedSlots_ = 0;    // the count

  public BoundedBufferVST(int capacity) 
   throws IllegalArgumentException {
    if (capacity <= 0) throw new IllegalArgumentException();
    array_ = new Object[capacity];
  }

  public int count() { return usedSlots_; }
  public int capacity() { return array_.length; }

  public synchronized void put(Object x) { 
    while (usedSlots_ == array_.length) // wait until not full
      try { wait(); } catch(InterruptedException ex) {}; 
    array_[putPtr_] = x;
    putPtr_ = (putPtr_ + 1) % array_.length; // cyclically inc
    if (usedSlots_++ == 0)
      notifyAll();
  }

  public synchronized Object take() { 
    while (usedSlots_ == 0) // wait until not empty
      try { wait(); } catch(InterruptedException ex) {}; 
    Object x = array_[takePtr_];
    array_[takePtr_] = null;
    takePtr_ = (takePtr_ + 1) % array_.length;
    if (usedSlots_-- == array_.length)
      notifyAll();
    return x;
  }
}

