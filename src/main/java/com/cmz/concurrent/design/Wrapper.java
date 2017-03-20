package com.cmz.concurrent.design;import java.awt.*;

public class Wrapper extends SingleOutputPushStage
                     implements PushStage {
  protected int thickness_;
  public Wrapper(int thickness) { 
    super(); 
    thickness_ = thickness; 
  }
  public synchronized void putA(Box p) {
    Dimension d = new Dimension(thickness_, thickness_);
    next1_.putA(new WrappedBox(p, d));
  }
}

