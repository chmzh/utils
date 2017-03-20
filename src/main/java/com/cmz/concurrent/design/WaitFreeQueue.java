package com.cmz.concurrent.design;
public final class WaitFreeQueue {
  private volatile WFQNodePtr head_;
  private volatile WFQNodePtr last_;

  public WaitFreeQueue() {
    WFQNode node = new WFQNode(null);
    head_ = new WFQNodePtr(node);
    last_ = new WFQNodePtr(node);
  }

  public void put(Object x) {
    boolean sameLast = false;
    WFQNode tlPtr = null;
    int tlCount = 0;
    WFQNode nxtPtr = null;
    int nxtCount = 0;

    WFQNode node = new WFQNode(x);
    for (;;) {
      
      // read ptr/count fields as a unit
      synchronized (last_) {
        tlPtr = last_.ptr; tlCount = last_.count;
      }
      nxtPtr = tlPtr.next.ptr;
      nxtCount = tlPtr.next.count;

      // check if nxt still corresponds to tail
      synchronized (last_) {
        sameLast = (tlPtr == last_.ptr && tlCount == last_.count);
      }

      // try to change next pointer of last node
      if (sameLast &&  nxtPtr == null  &&
          tlPtr.next.commit(nxtPtr, nxtCount, node, nxtCount + 1)) {
        // record change in tail
        last_.commit(tlPtr, tlCount, node, tlCount+1);
        return;
      }
      else { 
        //  change count to indicate failure
        last_.commit(tlPtr, tlCount, nxtPtr, tlCount+1);
        // Let another Thread run to reduce busy-wait contention
        Thread.currentThread().yield();
      }
    }
  }

  public Object take() {   // Returns null if empty 
    boolean sameHead = false;
    WFQNode hdPtr = null;
    int hdCount = 0;
    WFQNode tlPtr = null;
    int tlCount = 0;
    WFQNode firstPtr = null;
    for (;;) {

      // read ptr/count fields as a unit
      synchronized (head_) {
        hdPtr = head_.ptr; hdCount = head_.count;
      }
      synchronized (last_) {
        tlPtr = last_.ptr; tlCount = last_.count;
      }

      firstPtr = hdPtr.next.ptr;
      synchronized (head_) {
        sameHead = (hdPtr == head_.ptr && hdCount == head_.count);
      }

      if (sameHead) {
        if (hdPtr == tlPtr) {
          if (firstPtr == null) // empty
            return null;
          else                  // being updated
            last_.commit(tlPtr, tlCount, firstPtr, tlCount+1);
        }
        else {
          Object x = firstPtr.value;
          if (head_.commit(hdPtr, hdCount, firstPtr, hdCount+1)) {
            firstPtr.value = null;
            return x;
          }
        }
      }

      Thread.currentThread().yield();
    }
  }
}

final class WFQNode { // list nodes
  Object     value; 
  WFQNodePtr next;
  WFQNode(Object x) { value = x; next = new WFQNodePtr(null); }
}

final class WFQNodePtr { // pointers to list nodes
  volatile WFQNode  ptr;
  volatile int      count;
  WFQNodePtr(WFQNode n) { ptr = n; count = 0; }
  synchronized boolean commit(WFQNode assumedPtr, int assumedCount,
                              WFQNode newPtr,     int newCount) {
    boolean success = (ptr == assumedPtr && count == assumedCount);
    if (success) {  ptr = newPtr; count = newCount; }
    return success;
  }
}
