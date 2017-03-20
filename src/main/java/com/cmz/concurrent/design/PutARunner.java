package com.cmz.concurrent.design;public class PutARunner implements Runnable {
  protected PushStage target_;
  protected Box arg_;
  public PutARunner(PushStage target, Box arg) {
    target_ = target; arg_ = arg;
  }
  public void run() { target_.putA(arg_); }
}

