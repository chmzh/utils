package com.cmz.concurrent.design;
import java.io.*;

public interface FileReaderClient {
  public void readCompleted(String filename);
  public void readFailed(String filename, IOException ioerr);
}
