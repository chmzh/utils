package com.cmz.concurrent.design;
public class GroundCounter {
  protected long count_; 
  protected GroundCounter(long c) { count_ = c; }
  protected long value_() { return count_; }
  protected void inc_() { ++count_; }
  protected void dec_() { --count_; }
}
