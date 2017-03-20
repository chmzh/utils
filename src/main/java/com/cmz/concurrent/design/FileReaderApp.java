package com.cmz.concurrent.design;
import java.io.*;

public class FileReaderApp implements FileReaderClient {
  private byte[] data_;
  public void readCompleted(String fn) {
    System.out.println("Read Completed of " + fn);
  }
  public void readFailed(String fn, IOException ioerr) { 
    System.out.println("Read Failed for " + fn);
  }

 public void app() { // main method... 
    FileReader f = new FileReader("afile", data_, this);
    new Thread(f).start();
  }
}
