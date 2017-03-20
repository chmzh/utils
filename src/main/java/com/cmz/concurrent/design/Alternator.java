package com.cmz.concurrent.design;
public class Alternator extends DualOutputPushStage
                        implements PushStage {
  protected boolean outTo2_ = false; // control alternation
  public synchronized void putA(Box p) {
    if (!outTo2_) 
       next1_.putA(p);
    else 
       new Thread(new PutARunner(next2_, p)).start();
    outTo2_ = !outTo2_; // change state for next time
  }
}
