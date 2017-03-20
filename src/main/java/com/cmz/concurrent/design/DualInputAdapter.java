package com.cmz.concurrent.design;
public class DualInputAdapter implements PushStage {
  protected DualInputPushStage stage_;
  public DualInputAdapter(DualInputPushStage stage) { 
    stage_ = stage;
  }
  public void putA(Box p) { stage_.putB(p); }
}

