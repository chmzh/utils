package com.cmz.concurrent.design;public class PutTakeConnector { 

  boolean putting_ = false; // to disable concurrent puts
  Object item_ = null;      // require that actual items be non-null
 
  public synchronized void put(Object e) { 
    if (e == null) return; // nulls are not items 
    while (putting_)
    // wait for another put to complete
      try { wait(); } catch (InterruptedException ex) {}

    putting_ = true;        // record execution state 
    item_ = e;              // transfer item 
    notifyAll();            // allow take 
    while (item_ !=  null)  // wait for take
      try { wait(); }  catch (InterruptedException ex) {}
    putting_ = false; 
    notifyAll();            // enable another put
  }

  public synchronized Object take() {
    while (item_ == null)   // wait for put
      try { wait(); } catch (InterruptedException ex) {}
   Object e = item_;        // transfer item 
   item_ = null;
   notifyAll();             // release current put
   return e;
  }
} 
