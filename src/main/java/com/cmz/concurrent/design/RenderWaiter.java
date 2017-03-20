package com.cmz.concurrent.design;class RenderWaiter implements Runnable { 
  private PictureRenderer r_;   // service object
  private byte [] arg_;         // arguments to its method
  private Picture rslt_ = null; // results from its method

  RenderWaiter(PictureRenderer r, byte[] raw) {
    r_ = r; arg_ = raw;
  }

  synchronized Picture result() { return rslt_; }

  public void run() { 
    rslt_ = r_.render(arg_);
  }
}
