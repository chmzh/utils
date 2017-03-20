package com.cmz.concurrent.design;
public class Flipper extends SingleOutputPushStage
                     implements PushStage {
  public synchronized void putA(Box p) {
    if (p instanceof JoinedPair) 
      ((JoinedPair) p).flip();
    next1_.putA(p);
  }
}
