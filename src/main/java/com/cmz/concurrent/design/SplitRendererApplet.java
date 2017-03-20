package com.cmz.concurrent.design;import java.awt.*;
import java.applet.*;

public class SplitRendererApplet extends PictureDisplayV1Applet {

  public SplitRendererApplet() {
    txt_ = new TextArea(4, 40); // 4 rows, 40 columns
    displayer_ = new PictureDisplayV2(txt_, 
                                      new SplitRenderer());
  }

}
