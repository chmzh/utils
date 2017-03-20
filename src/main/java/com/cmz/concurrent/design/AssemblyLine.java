package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class AssemblyLine extends Applet implements PushStage {

  Box current_;  // the box currently being displayed

  // This assembly line has four sources:
  BasicBoxSource source1;
  BasicBoxSource source2;
  BasicBoxSource source3;
  BasicBoxSource source4;

  public AssemblyLine() {
    current_ = null;

    source1 = new BasicBoxSource(new Dimension(50, 30), 1000);
    Painter painter1 = new Painter(Color.red);
    source1.attach1(painter1); 

    Alternator alternator1 = new Alternator();
    painter1.attach1(alternator1);

    HorizontalJoiner horizontalJoiner1 = new HorizontalJoiner();
    DualInputAdapter adaptor1 = new DualInputAdapter(horizontalJoiner1);

    HorizontalJoiner horizontalJoiner2 = new HorizontalJoiner();
    DualInputAdapter adaptor2 = new DualInputAdapter(horizontalJoiner2);

    alternator1.attach1(horizontalJoiner1);
    alternator1.attach2(horizontalJoiner2);

    source2 = new BasicBoxSource(new Dimension(80, 60), 1000);
    Painter painter2 = new Painter(Color.green);
    source2.attach1(painter2);
    painter2.attach1(adaptor1);

    Collector merger1 = new Collector();
    DualInputAdapter adaptor5 = new DualInputAdapter(merger1);

    Flipper flipper1 = new Flipper();
    horizontalJoiner1.attach1(flipper1);
    flipper1.attach1(merger1);

    horizontalJoiner2.attach1(adaptor5);

    Wrapper wrapper1 = new Wrapper(6);
    merger1.attach1(wrapper1);

    Painter painter4 = new Painter(Color.orange);
    wrapper1.attach1(painter4);

    VerticalJoiner verticalJoiner1 = new VerticalJoiner();
    DualInputAdapter adaptor3 = new DualInputAdapter(verticalJoiner1);
    painter4.attach1(verticalJoiner1);

    Wrapper wrapper2 = new Wrapper(4);
    verticalJoiner1.attach1(wrapper2);

    Painter painter5 = new Painter(Color.yellow);
    wrapper2.attach1(painter5);

    Cloner cloner1 = new Cloner();
    painter5.attach1(cloner1);

    source3 = new BasicBoxSource(new Dimension(70, 120), 1000);
    Painter painter3 = new Painter(Color.blue);
    source3.attach1(painter3);
    painter3.attach1(adaptor3);

    source4 = new BasicBoxSource(new Dimension(40, 80), 1000);
    Painter painter6 = new Painter(Color.black);
    source4.attach1(painter6);
    painter6.attach1(adaptor2);


    HorizontalJoiner horizontalJoiner3 = new HorizontalJoiner();
    DualInputAdapter adaptor4 = new DualInputAdapter(horizontalJoiner3);
    cloner1.attach1(horizontalJoiner3);
    cloner1.attach2(adaptor4);

    Cloner cloner2 = new Cloner();
    horizontalJoiner3.attach1(cloner2);
    
    VerticalJoiner verticalJoiner2 = new VerticalJoiner();
    DualInputAdapter adaptor6 = new DualInputAdapter(verticalJoiner2);
    cloner2.attach1(verticalJoiner2);

    Flipper flipper2 = new Flipper();
    cloner2.attach2(flipper2);
    flipper2.attach1(adaptor6);

    Screener sizeScreener1 = new Screener(new MaxSizePredicate(200));
    DevNull discards = new DevNull();

    verticalJoiner2.attach1(sizeScreener1);
    sizeScreener1.attach1(this);
    sizeScreener1.attach2(discards);
    
  }

  Thread t1;
  Thread t2;
  Thread t3;
  Thread t4;

  public void start() {
    t1 = new Thread(source1);
    t2 = new Thread(source2);
    t3 = new Thread(source3);
    t4 = new Thread(source4);

    t1.setPriority(Thread.MIN_PRIORITY + 1);
    t2.setPriority(Thread.MIN_PRIORITY + 1);
    t3.setPriority(Thread.MIN_PRIORITY + 1);
    t4.setPriority(Thread.MIN_PRIORITY + 1);

    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }

  public synchronized void putA(Box p) {
    // hold up until the previous display is finished
    while (current_ != null) {
      try { wait(); } catch (InterruptedException ex) { return; }
    }
    current_ = p;
    repaint();
  }

  public synchronized void paint(Graphics g) {
    if (current_ != null) {
      current_.show(g, new java.awt.Point(0, 0));
      current_ = null;
      notifyAll();
    }
  }
  
  public synchronized void stop() {
    t1.interrupt();
    t2.interrupt();
    t3.interrupt();
    t4.interrupt();
  }
    
}
