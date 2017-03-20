package com.cmz.concurrent.design;public class VerticalJoiner extends Joiner {
  protected synchronized Box join() {
    return new VerticallyJoinedPair(a_, b_);
  }
}
