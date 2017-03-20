package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class PrintApplet extends Applet {
  static final int nServices = 4;
  protected TextArea txt_;
  protected Printer printer_;
  protected Button[] buttons_;

  public PrintApplet() {
    txt_ = new TextArea(4, 40); // 4 rows, 40 columns
    printer_ = new Printer(txt_);
    PrintService.startUpServices(nServices, printer_);
  }

  public void init() {
    add(txt_); // add text area to applet display
    Panel p = new Panel();
    buttons_ = new Button[nServices];
    for (int i = 0; i < nServices; ++i) {
      buttons_[i] = new Button("Service "+ i);
      p.add(buttons_[i]);
    }
    add(p);
  }

  public boolean action(Event evt, Object arg) {
    for (int i = 0; i < nServices; ++i) {
      if (evt.target == buttons_[i]) {
        new Thread(new PrintRunner(i)).start();
        return true;
      }
    }
    return false;
  }

}

class PrintRunner implements Runnable {
  static final long sleepTime = 1000;
  static int seq_ = 0;
  protected int index_;

  PrintRunner(int i) { index_ = i; }

  public void run() {
    try { Thread.currentThread().sleep((long)(Math.random() * sleepTime)); }
    catch (InterruptedException ex) { return; }
          
    String msg = "\nPrinting job " + seq_ + " using Service " + index_;
    ++seq_;
    PrintService s = PrintService.at(index_);
    s.print(msg);
  }
}
