package com.cmz.concurrent.design;
import java.awt.*;

public class PictureDisplayV2 extends PictureDisplayV1 { 
  public PictureDisplayV2(TextArea canvas, PictureRenderer r) { 
    super(canvas, r); 
  }

  public void show(byte[] rawPicture) {
    FutureRenderer r = new FutureRenderer(myRenderer_);
    r.render(rawPicture);
    displayBorders();
    displayCaption();
    displayPicture(r.result());
  }
}

