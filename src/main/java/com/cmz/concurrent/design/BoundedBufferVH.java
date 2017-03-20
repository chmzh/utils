package com.cmz.concurrent.design;
public final class BoundedBufferVH implements BoundedBuffer {
  private Object[] array_;
  private BBExchanger putter_;
  private BBExchanger taker_;


  public BoundedBufferVH(int capacity) 
   throws IllegalArgumentException {
    if (capacity <= 0) throw new IllegalArgumentException();
    array_ = new Object[capacity];
    putter_ = new BBExchanger(this, array_, capacity);
    taker_ = new BBExchanger(this, array_, 0);
  }

  public int count() { return taker_.slots(); }
  public int capacity() { return array_.length; }

  public void put(Object x) { putter_.exchange(x); }
  public Object take() { return taker_.exchange(null); }

  void removedSlotNotification(BBExchanger h) { // relay
    if (h == putter_) 
      taker_.addedSlotNotification(); 
    else if (h == taker_) 
      putter_.addedSlotNotification();
  }
}


class BBExchanger {
  protected BoundedBufferVH host_; // the coordinator
  protected Object[]  array_;      // shared representation
  protected int ptr_ = 0;          // circular index
  protected int slots_;            // number of usable slots
  protected int waiting_ = 0;      // number of waiting threads


  BBExchanger(BoundedBufferVH h, Object[] a, int slots) {
    host_ = h; 
    array_ = a;
    slots_ = slots;
  }

  int slots() { return slots_; }


  synchronized void addedSlotNotification() { 
    ++slots_;
    if (waiting_ > 0) // unblock a single waiting thread
       notify();
  }


  Object exchange(Object x) { // replace old with x; advance
    Object old = null; // return value
    synchronized(this) { 
      while (slots_ <= 0) { // wait for slot
        ++waiting_;
        try { wait(); } catch(InterruptedException ex) {}; 
        --waiting_;
      }
      --slots_;             // use slot
      old = array_[ptr_];
      array_[ptr_] = x;
      ptr_ = (ptr_ + 1) % array_.length; // advance position
    }
    host_.removedSlotNotification(this);
    return old;
  }
}

