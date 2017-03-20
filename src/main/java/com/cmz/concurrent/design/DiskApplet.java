package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class DiskApplet extends Applet {
  final static int nThreads = 5;
  protected DisplayableScheduledDisk disk_;
  protected DiskGraphic dg_;
  protected AccessRunner[] runners_;
  protected Thread[] threads_;
  protected Button startstop_ = new Button("start/stop");
  protected boolean started_ = false;

  public DiskApplet() {
    dg_ = new DiskGraphic();
    disk_ = new DisplayableScheduledDisk(dg_);
  }

  public void init() {
    add(dg_);
    Panel p1 = new Panel();
    runners_ = new AccessRunner[nThreads];
    threads_ = new Thread[nThreads];
    for (int i = 0; i < nThreads; ++i) {
      runners_[i] = new AccessRunner(disk_);
      p1.add(runners_[i]);
    }
    add(p1);
    
    startstop_.setBackground(Color.red);
    add(startstop_);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target == startstop_) {
      if (!started_) {
        startstop_.setBackground(Color.green);
        started_ = true;
        for (int i = 0; i < nThreads; ++i) {
          threads_[i] = new Thread(runners_[i]);
          threads_[i].start();
        }
      }
      else {
        startstop_.setBackground(Color.red);
        started_ = false;
        for (int i = 0; i < nThreads; ++i) {
          threads_[i].stop();
        }

      }
      return true;
    }
    return false;
  }


}

class AccessRunner extends java.awt.Label implements Runnable {
  static final int MAXCYL = 1000;
  static final long sleepTime = 10000;
  static final Color idleColor = Color.lightGray;
  static final Color stallColor = Color.red;
  static final Color okColor = Color.green;
  protected  DisplayableScheduledDisk disk_;

  AccessRunner(DisplayableScheduledDisk disk) {
    super("           ");
    disk_ = disk;
    setBackground(idleColor);
  }

  public void run() {
    byte[] buff = new byte[1];
    for (;;) {
      int cyl = (int)(Math.random() * MAXCYL);
      setBackground(stallColor);
      setText(" " + cyl);
      disk_.write(cyl, buff);
      try {
        setText("           ");
        setBackground(okColor);
        Thread.currentThread().sleep((long)(Math.random() * sleepTime));
      }
      catch(InterruptedException ex) {}
    }
  }
}


class DisplayableScheduledDisk extends ScheduledDisk {
  protected DiskGraphic dg_;

  DisplayableScheduledDisk(DiskGraphic dg) { dg_ = dg; }

  public byte[] read(int cyl) {
    byte[] b = super.read(cyl);
    dg_.setCylinder(cyl);
    return b;
  }

  public void write(int cyl, byte[] buffer) {
    super.write(cyl, buffer);
    dg_.setCylinder(cyl);
  }
}



class DiskGraphic extends Canvas {
  protected int cyl_ = 0;
  static final int MINCYL = 0;
  static final int MAXCYL = 1000;

  static final int WIDTH=20;
  static final int HEIGHT = 100;

  DiskGraphic() {
    resize(WIDTH+1, HEIGHT+1);
  }

  public void setCylinder(int c) { cyl_ = c; repaint(); }

  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.drawRect(0, 0, WIDTH, HEIGHT);
    g.setColor(Color.blue);
    int ht = (int) 
      (( (float)(cyl_ - MINCYL) / 
        (float)(MAXCYL - MINCYL))
        * HEIGHT);
    if (ht > HEIGHT) ht = HEIGHT;
    else if (ht < 0) ht = 0;

    g.fillRect(0, HEIGHT - ht, WIDTH, ht);
  }
}
