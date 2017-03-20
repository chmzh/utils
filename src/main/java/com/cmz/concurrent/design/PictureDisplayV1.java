package com.cmz.concurrent.design;
import java.awt.*;

public class PictureDisplayV1 {
  protected PictureRenderer myRenderer_;
  TextArea canvas_;

  public PictureDisplayV1(TextArea canvas, PictureRenderer r) { 
    canvas_ = canvas; 
    myRenderer_ = r;
  }

  public void show(byte[] rawPicture) {
    // create and start thread
    RenderWaiter w = new RenderWaiter(myRenderer_, rawPicture);
    Thread t = new Thread(w);
    t.start();
    // do some other things, like:
    displayBorders();
    displayCaption();

    try {    // wait for thread to complete
      t.join();
    }
    catch(InterruptedException ex) {
      cleanUp();
      return;
    }

  Picture img = w.result(); // get the result from the waiter
    displayPicture(img);
  }

  protected void displayBorders() {
    canvas_.appendText("|---- a border ----|\n");
  }

  protected void displayCaption() {
    canvas_.appendText("... a caption ...\n");
  }

  protected void displayPicture(Picture img) {
    canvas_.appendText(img.image());
  }
  
  protected void cleanUp() {
    canvas_.appendText("Broken join. Cleaning up.\n");
  }
}


