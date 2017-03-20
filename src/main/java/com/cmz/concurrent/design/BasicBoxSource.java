package com.cmz.concurrent.design;
import java.awt.*;

public class BasicBoxSource extends SingleOutputPushStage 
                            implements PushSource, Runnable {
  protected Dimension size_;
  protected int productionTime_;
  public BasicBoxSource(Dimension size, int productionTime) { 
    size_ = size;     
    productionTime_ = productionTime; 
  }

  protected  Box produce() {
    return new BasicBox((int)(Math.random() * size_.width) + 1,
                        (int)(Math.random() * size_.height) + 1);
  }


  public  void start() { next1_.putA(produce()); }

  public void run() {
    for (;;) {
      start();
      try {
        Thread.sleep((int)(Math.random() * 2*productionTime_));
      }
      catch (InterruptedException ex) { return; }
    }
  }
}
