package com.cmz.concurrent.design;
import java.util.NoSuchElementException;

public class ExpandableArray {
  private Object[] data_; // the elements
  private int size_; // the number of slots used in the array

  public ExpandableArray(int cap) {
    data_ = new Object[cap]; 
    size_ = 0;
  }

  public synchronized int size() { return size_; }

  public synchronized Object at(int i) // subscripted access
   throws NoSuchElementException {
    if (i < 0 || i >= size_ ) 
      throw new NoSuchElementException();
    else
      return data_[i];
  }

  public synchronized void append(Object x) { // add at end
    if (size_ >= data_.length) { // need a bigger array
      Object[] olddata = data_;
      data_ = new Object[3 * (size_ + 1) / 2];
      for (int i = 0; i < size_; ++i) 
        data_[i] = olddata[i];
    }
    data_[size_++] = x;
  }

  public synchronized void removeLast() 
   throws NoSuchElementException {
    if (size_ == 0) 
      throw new NoSuchElementException();
    else
      data_[--size_] = null;
  }
}


