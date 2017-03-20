package com.cmz.concurrent.design;
public class Inventory extends GroundInventory {
  protected int storing_ = 0;    // number of in-progress stores
  protected int retrieving_ = 0; // number of retrieves
  // ...
  public void store(String description, Object item,
                    String supplier) {

    synchronized(this) { ++storing_; }   // record exec state
    store_(description, item, supplier); // ground action
    synchronized(this) {                 // signal retrieves
      if (--storing_ == 0) 
        notifyAll();
    }
  }

  public Object retrieve(String description) {
    // wait until no other stores or retrieves
    synchronized(this) { 
      while (storing_ != 0 || retrieving_ != 0) 
        try { wait(); } catch (InterruptedException ex) {}
       ++retrieving_;
    }
    Object x = retrieve_(description); // ground action
    synchronized(this) {               // signal others
      if (--retrieving_ == 0)
        notifyAll();
    }
    return x;
  }
}

