package com.cmz.concurrent.design;
import java.awt.*;

public class PictureDisplayV3 extends PictureDisplayV1 {

  public PictureDisplayV3(TextArea canvas, PictureRenderer r) { 
    super(canvas, r); 
  }

  public void show(byte[] rawPicture) {
    // create/start thread
    RenderWaiter w = new RenderWaiter(myRenderer_, rawPicture);
    Thread t = new Thread(w);
    t.start();

    // do other things
    displayBorders();
    displayCaption();

    // wait for either completion or time-out
    try {
      t.join(1000);
    }
    catch(InterruptedException ex) {
      cleanUp();
      return;
    }
    t.stop(); // no effect if already done
    Picture img = w.result(); // will be null if didn't complete

    // take appropriate action
    if (img == null)
      cleanUp();
    else
      displayPicture(img);
  }
}

