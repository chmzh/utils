package com.cmz.concurrent.design;
public class DualOutputPushStage extends SingleOutputPushStage {
  protected PushStage next2_ = null;
  public void attach2(PushStage s) { next2_ = s; }
}

