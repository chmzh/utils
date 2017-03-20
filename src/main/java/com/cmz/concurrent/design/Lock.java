package com.cmz.concurrent.design;
public interface Lock {
  // associate key with lock
  public void acquire(Object key) throws KeyException;

  // return true if key is associated with lock

  public boolean hasKey(Object key);

  // access protected code
  public void enter(Object key) throws KeyException;

  // get rid of key (no exception; ignore if not applicable)
  public void release(Object key);

}

