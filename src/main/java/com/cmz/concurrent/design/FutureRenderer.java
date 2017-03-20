package com.cmz.concurrent.design;
public class FutureRenderer implements Runnable {
  private PictureRenderer r_; // same setup as Waiter version
  private byte [] arg_ = null;
  private Picture rslt_ = null;
  private Thread thread_ = null;

  public FutureRenderer(PictureRenderer r) {
    r_ = r; 
  }

  public synchronized void render(byte[] raw) {

    // This version supports only one invocation at a time 
    while (thread_ != null) 
      try { wait(); } catch (InterruptedException ex) {} 

    arg_ = raw;
    rslt_ = null;
    thread_ = new Thread(this);
    thread_.start();
  }

  public  void run() {
    // prevent abuse
    if (Thread.currentThread() != thread_) return;
    rslt_ = r_.render(arg_);
  }

  public synchronized Picture result() {
    if (thread_ == null) return null;
    try { thread_.join(); }
    catch(InterruptedException ex) { return null; }
    thread_ = null; 
    notifyAll(); // unblock new requests
    return rslt_;
  }
}

