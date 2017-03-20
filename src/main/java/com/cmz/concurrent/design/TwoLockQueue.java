package com.cmz.concurrent.design;final class TLQNode { // local node class for queue
  Object value;
  TLQNode next;
  TLQNode(Object x, TLQNode n) { value = x; next = n; }
}

public class TwoLockQueue {
  private TLQNode head_;    // pointer to dummy header node
  private TLQNode last_;    // pointer to last node
  private Object lastLock_; // protect access to last

  public TwoLockQueue() {
    head_ = last_ = new TLQNode(null, null);
    lastLock_ = new Object();
  }

  public void put(Object x) {
    TLQNode node = new TLQNode(x, null);
    synchronized (lastLock_) {  // insert at end of list
      last_.next = node;
      last_ = node;
    }
  }

  public synchronized Object take() { // returns null if empty
    Object x = null; // return value
    TLQNode first = head_.next; // first real node is after head

    if (first != null) {
      x = first.value;
      head_ = first; // old first becomes new head
    }
    return x;
  }
}

