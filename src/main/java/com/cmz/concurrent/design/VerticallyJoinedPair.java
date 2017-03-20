package com.cmz.concurrent.design;import java.awt.*;

public class VerticallyJoinedPair extends JoinedPair {

  public VerticallyJoinedPair(Box u, Box d) { super(u, d); }

  public Dimension size() {  
    return new Dimension(maxWidth()+1, sumHeight()+2); 
  }

  public void show(Graphics g, java.awt.Point origin) {
    g.setColor(color_);
    Dimension sz = size();
    g.drawRect(origin.x, origin.y, sz.width, sz.height);
    fst_.show(g, new java.awt.Point(origin.x + 1, origin.y + 1));
    snd_.show(g,
	      new java.awt.Point(origin.x + 1, 
				 origin.y + fst_.size().height + 1));
  }

  public Box duplicate() { 
    VerticallyJoinedPair p = 
      new VerticallyJoinedPair(fst_.duplicate(), snd_.duplicate());
    p.color(color_);
    return p;
  }

}
