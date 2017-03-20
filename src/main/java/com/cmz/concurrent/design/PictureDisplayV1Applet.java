package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class PictureDisplayV1Applet extends Applet {
  protected TextArea txt_;
  protected PictureDisplayV1 displayer_;
  protected Button start_ = new Button("start");
  protected int seq_ = 0;

  public PictureDisplayV1Applet() {
    txt_ = new TextArea(4, 40); // 4 rows, 40 columns
    displayer_ = new PictureDisplayV1(txt_,
                                      new DumbPictureRenderer());
  }

  public void init() {
    add(txt_); // add text area to applet display
    add(start_);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target == start_) {
      String s = "Image number " + seq_ + "\n";
      ++seq_;
      int len = s.length();
      byte[] b = new byte[len];
      s.getBytes(0, len, b, 0);
      displayer_.show(b);
      return true;
    }
    return false;
  }


}
