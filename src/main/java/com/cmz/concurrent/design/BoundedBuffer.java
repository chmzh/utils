package com.cmz.concurrent.design;
public interface BoundedBuffer {
  public int    capacity(); // invariant: 0 <= capacity
  public int    count();    // invariant: 0 <= count <= capacity
  public void   put(Object x); // add only when count < capacity
  public Object take();     // remove only when count > 0
}
