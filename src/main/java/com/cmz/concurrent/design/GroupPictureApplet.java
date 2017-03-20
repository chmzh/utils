package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class GroupPictureApplet extends Applet {
  protected TextArea txt_;
  protected GroupPictureRenderer displayer_;
  protected Button start_ = new Button("start");
  protected int seq_ = 0;

  public GroupPictureApplet() {
    txt_ = new TextArea(4, 40); // 4 rows, 40 columns
    displayer_ = new GroupPictureRenderer(txt_,
                                         new DumbPictureRenderer());
  }

  public void init() {
    add(txt_); // add text area to applet display
    add(start_);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target == start_) {
      byte[] [] b = new byte[3][];
      for (int i = 0; i < 3; ++i) {
        String s = "Image number " + seq_ + "\n";
        ++seq_;
        int len = s.length();
        b[i] = new byte[len];
        s.getBytes(0, len, b[i], 0);
      }
      displayer_.show(b);
      return true;
    }
    return false;
  }


}
