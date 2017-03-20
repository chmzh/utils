package com.cmz.concurrent.design;
public class Collector extends SingleOutputPushStage
                       implements DualInputPushStage {
  public synchronized void putA(Box p) { next1_.putA(p); }
  public synchronized void putB(Box p) { next1_.putA(p); }
}

