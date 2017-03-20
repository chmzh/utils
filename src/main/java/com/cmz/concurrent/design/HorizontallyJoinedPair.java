package com.cmz.concurrent.design;
import java.awt.*;

public class HorizontallyJoinedPair extends JoinedPair {

  public HorizontallyJoinedPair(Box l, Box r) { super(l, r); }

  public Dimension size() {  
    return new Dimension(sumWidth()+2, maxHeight()+1); 
  }

  public void show(Graphics g, java.awt.Point origin) {
    g.setColor(color_);
    Dimension sz = size();
    g.drawRect(origin.x, origin.y, sz.width, sz.height);
    fst_.show(g, new java.awt.Point(origin.x + 1, origin.y + 1));
    snd_.show(g, 
	      new java.awt.Point(origin.x + fst_.size().width + 1, 
				 origin.y + 1));
  }

  public Box duplicate() { 
    HorizontallyJoinedPair p = 
      new HorizontallyJoinedPair(fst_.duplicate(), snd_.duplicate());
    p.color(color_);
    return p;
  }

}
