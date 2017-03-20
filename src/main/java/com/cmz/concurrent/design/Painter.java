package com.cmz.concurrent.design;
import java.awt.*;

public class Painter extends SingleOutputPushStage
                     implements PushStage {
  protected Color color_;
  public Painter(Color c) { super(); color_ = c;  }
  public  void putA(Box p) {
    p.color(color_);
    next1_.putA(p);
  }
}
