package com.cmz.concurrent.design;
import java.awt.*;

public class MaxSizePredicate implements BoxPredicate {
  private int max_ ;
  public MaxSizePredicate(int max) { max_ = max; }
  public boolean test(Box p) {
    return p.size().height <= max_ && p.size().width <= max_;
  }
}
