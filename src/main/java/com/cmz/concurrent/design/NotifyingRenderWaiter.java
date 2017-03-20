package com.cmz.concurrent.design;
public class NotifyingRenderWaiter implements Runnable {
  private PictureRenderer r_;
  private byte [] arg_;
  private Picture rslt_ = null;
  private NotifyingRendererClient caller_;
  public NotifyingRenderWaiter(NotifyingRendererClient caller,
                               PictureRenderer r, byte[] raw) {
    caller_ = caller; r_ = r; arg_ = raw; 
  }
  public Picture result() { return rslt_; }
  public synchronized void run() {
    rslt_ = r_.render(arg_);
    caller_.renderDone(rslt_);
  }
}

