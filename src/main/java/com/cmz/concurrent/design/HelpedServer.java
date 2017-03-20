package com.cmz.concurrent.design;
public class HelpedServer {
  protected Helper helper_; // latch, unique

  public HelpedServer() {
    helper_ = null;
    new Thread(new HelperInitializer(this)).start();
  }

  // callback from initializer
  synchronized void initializeHelper(Helper h) {
    if (helper_ == null) {
      helper_ = h;
      notifyAll();
    }
  }

  // get helper reference. If null, wait for it.
  protected Helper helper() {
    if (helper_ == null)  {
      synchronized(this) {
        while (helper_ == null)
          try { wait(); } catch(InterruptedException ex) {}
      }
    }
    return helper_;
  }

  // Use helper, for example in:
  public void delegatedAction() { helper().help(); }
}

class HelperInitializer implements Runnable {
  protected HelpedServer s_;
  HelperInitializer(HelpedServer s) { s_ = s; }
  public void run() {
    Helper h = new Helper();
    s_.initializeHelper(h);
  }
}

class Helper { // just a stub
  void help() {}
}
