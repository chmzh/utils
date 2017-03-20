package com.cmz.concurrent.design;public class SplitRenderer implements PictureRenderer {
  PictureRenderer renderer1_; //  group member 1
  PictureRenderer renderer2_; //  group member 2

  public SplitRenderer() {
    renderer1_ = new DumbPictureRenderer();
    renderer2_ = new DumbPictureRenderer();
  }

  public Picture render(byte[] rawPicture) {
    // split
    byte[] rawLeft = leftHalf(rawPicture);
    byte[] rawRight = rightHalf(rawPicture);

    // start threads
    RenderWaiter leftRenderer = 
      new RenderWaiter(renderer1_, rawLeft);
    RenderWaiter rightRenderer = 
      new RenderWaiter(renderer2_, rawRight);
    Thread leftthread = new Thread(leftRenderer);
    Thread rightthread = new Thread(rightRenderer);
    leftthread.start();
    rightthread.start();

    // join both of them
    try {
      leftthread.join();    // (order doesn't matter)
      rightthread.join();
    }
    catch(InterruptedException ex) {
      leftthread.stop();
      rightthread.stop();
      return null;
    }

    // use results
    Picture leftImg = leftRenderer.result();
    Picture rightImg = rightRenderer.result();
    return combinePictures(leftImg, rightImg);
  }

  byte[] leftHalf(byte[] arr) {
    int len = arr.length / 2;
    byte [] b = new byte[len];
    for (int i = 0; i < len; ++i) 
      b[i] = arr[i];
    return b;
  }

  byte[] rightHalf(byte[] arr) {
    int start = arr.length / 2;
    int len = arr.length - start;
    byte [] b = new byte[len];
    int j = start;
    for (int i = 0; i < len; ++i) 
      b[i] = arr[j++];
    return b;
  }

  Picture combinePictures(Picture a, Picture b) {
    return new Picture(a.image() + b.image());
  }

}
