package com.cmz.concurrent.design;
import java.io.*;

public class FileReader implements Runnable {
  protected String nm_;     // hold arguments
  protected byte[] d_;
  protected FileReaderClient client_; // callback target

  // constructor for callback version
  public FileReader(String name, byte[] data, 
                    FileReaderClient c){
    nm_ = name; d_ = data; client_ = c;
  }

  // constructor for no-callback version
  public FileReader(String name, byte data[]) { 
    this(name, data, null);
  }

  public synchronized void read() {
    try {
      if (Math.random() < 0.1) // simulate
        throw new IOException();
      if (client_ != null) 
        client_.readCompleted(nm_);
    }
    catch (IOException ex) {
      if (client_ != null) 
        client_.readFailed(nm_, ex);
    }
  }
  public void run() { read(); }
}
