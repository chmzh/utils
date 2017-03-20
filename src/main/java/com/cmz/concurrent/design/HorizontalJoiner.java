package com.cmz.concurrent.design;
public class HorizontalJoiner extends Joiner {
  protected synchronized Box join() {
    return new HorizontallyJoinedPair(a_, b_);
  }
}
