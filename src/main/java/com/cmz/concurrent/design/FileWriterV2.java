package com.cmz.concurrent.design;
import java.io.*;

public class FileWriterV2 implements Runnable {
  protected String nm_;
  protected byte[] d_;
  protected Runnable successCallback_;
  protected Runnable failureCallback_;
  public FileWriterV2(String name, byte[] data,
                      Runnable successCallback, 
                      Runnable failureCallback) {
   nm_ = name; 
   d_ = data;
   successCallback_ = successCallback;
   failureCallback_ = failureCallback;
  }

  public synchronized void write() {
    // ... try to write the file .. 
    if (Math.random() < 0.1) { // simulate
      if (failureCallback_ != null) 
        failureCallback_.run();
    }
    else {
      if (successCallback_ != null) 
        successCallback_.run();
    }
  }

  public void run() { write(); }
}
