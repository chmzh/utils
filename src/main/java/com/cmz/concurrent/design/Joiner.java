package com.cmz.concurrent.design;
public abstract class Joiner extends SingleOutputPushStage
                             implements DualInputPushStage {
  protected Box a_ = null;  // incoming box from putA
  protected Box b_ = null;  // incoming box from putB

  protected abstract Box join(); // differs in subclasses

  protected void output() {// pass on a completed pair 
    if (a_ != null && b_ != null) {
      next1_.putA(join()); // send combined box
      a_ = b_ = null;
      notifyAll(); // allow new puts
    }
  }

  public synchronized void putA(Box p) {
    // block until held a_ part is successfully joined with a b_
    while (a_ != null) 
      try { wait(); } catch (InterruptedException e) { return; }
    a_ = p;
    output();
  }

  public synchronized void putB(Box p) { // symmetrical to putA
    while (b_ != null)
      try { wait(); } catch (InterruptedException e) { return; }
    b_ = p;
    output();
  }
}


