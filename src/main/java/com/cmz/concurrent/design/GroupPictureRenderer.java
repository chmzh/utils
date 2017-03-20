package com.cmz.concurrent.design;
import java.awt.*;

public class GroupPictureRenderer 
             implements NotifyingRendererClient {
  protected PictureRenderer myRenderer_;
  protected int activeRenderers_ = 0;
  protected NotifyingRenderWaiter[] waiter_ = null;
  protected TextArea canvas_;

  public GroupPictureRenderer(TextArea canvas, PictureRenderer r) { 
    canvas_ = canvas; 
    myRenderer_ = r;
  }

  public synchronized void renderDone(Picture result) {
    displayPicture(result); // use results
    if (--activeRenderers_ == 0) 
      notifyAll();
  }

  public synchronized void show(byte[] [] rawPictures) {
    // only support one call at a time; even if blocked below
    while (waiter_ != null)
      try { wait(); } catch(InterruptedException ex) { return; }

    // fire up threads
    activeRenderers_ = rawPictures.length;
    waiter_ = new NotifyingRenderWaiter[activeRenderers_];
    // not appropriate for applets...
    // ThreadGroup tg = new ThreadGroup("Renderers");
    for(int i = 0; i < rawPictures.length; i++) {
      waiter_[i] = new NotifyingRenderWaiter(this, myRenderer_, rawPictures[i]);
      new Thread(waiter_[i]).start();
      //      (new Thread(tg, waiter_[i])).start();
    }

    // wait until receive all callbacks
    while (activeRenderers_ != 0)
      try { wait(); } catch(InterruptedException ex) { return; }

    // reset for next request
    waiter_ = null;
    notifyAll(); // unblock new show() call
  }

  protected void displayPicture(Picture img) {
    canvas_.appendText(img.image());
  }

}

