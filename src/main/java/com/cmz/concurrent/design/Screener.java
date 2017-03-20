package com.cmz.concurrent.design;public class Screener extends DualOutputPushStage
                      implements PushStage {
  BoxPredicate pred_;
  public Screener(BoxPredicate pred) { 
    super(); 
    pred_ = pred; 
  }
  public synchronized void putA(Box p) {
    if (pred_.test(p))
      new Thread(new PutARunner(next1_, p)).start();
    else
      next2_.putA(p);
  }
}


