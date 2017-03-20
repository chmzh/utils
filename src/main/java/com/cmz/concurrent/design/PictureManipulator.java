package com.cmz.concurrent.design;
class SuccessfulWriteHandler implements Runnable {
  PictureManipulator target_; // just relay the callback to target
  public SuccessfulWriteHandler(PictureManipulator x) {
    target_ = x;
  }
  public void run() { target_.successfulWrite(); }
}


class FailedWriteHandler implements Runnable {
  PictureManipulator target_;
  public FailedWriteHandler(PictureManipulator x) {
    target_ = x;
  }
  public void run() { target_.failedWrite(); }
}

public class PictureManipulator {
  void successfulWrite() { 
    System.out.println("Success");
  }
  void failedWrite() { 
    System.out.println("Failed");
  }
  // ...
  void processPicture(byte rawPicture[]) {
    Runnable s = new SuccessfulWriteHandler(this);
    Runnable f = new FailedWriteHandler(this);
    new Thread(new FileWriterV2("PictureCache", 
                                rawPicture, s,f)).start();
    // 
    // ...
  }
}
