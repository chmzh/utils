package com.cmz.concurrent.design;
public class Cloner extends DualOutputPushStage
                    implements PushStage {
  public void putA(Box p) {
    Box p2 = p.duplicate();
    new Thread(new PutARunner(next1_, p)).start();
    next2_.putA(p2);
  }
}

