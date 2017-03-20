package com.cmz.concurrent.design;
import java.awt.*;

public abstract class JoinedPair extends Box {
  protected Box fst_;
  protected Box snd_;

  protected JoinedPair(Box fst, Box snd) {
    super();
    fst_ = fst;
    snd_ = snd;
  }

  public void flip() {
    Box tmp = fst_; fst_ = snd_; snd_ = tmp;
  }

  protected int maxWidth() { 
    Dimension fs = fst_.size(); Dimension ss = snd_.size();
    return fs.width > ss.width ? fs.width : ss.width;
  }

  protected int maxHeight() { 
    Dimension fs = fst_.size(); Dimension ss = snd_.size();
    return fs.height > ss.height ? fs.height : ss.height;
  }

  protected int sumWidth() { 
    Dimension fs = fst_.size(); Dimension ss = snd_.size();
    return fs.width + ss.width;
  }

  protected int sumHeight() { 
    Dimension fs = fst_.size(); Dimension ss = snd_.size();
    return fs.height + ss.height;
  }

}

