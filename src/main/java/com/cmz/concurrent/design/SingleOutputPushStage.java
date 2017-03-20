package com.cmz.concurrent.design;public class SingleOutputPushStage {
  protected PushStage next1_ = null;
  public void attach1(PushStage s) { next1_ = s; }
}
