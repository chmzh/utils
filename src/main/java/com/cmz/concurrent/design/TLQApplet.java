package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;
public class TLQApplet extends Applet {
  protected TextArea txt_;
  protected TLQProducer producer_;
  protected TLQConsumer consumer_;
  protected TwoLockQueue tlq_;
  protected Button startstop_ = new Button("start/stop");
  protected boolean started_ = false;
  protected Thread pt_;
  protected Thread ct_;

  public TLQApplet() {
    txt_ = new TextArea(20, 20); // 20 rows, 20 columns
    tlq_ = new TwoLockQueue();
    producer_ = new TLQProducer(tlq_, txt_);
    consumer_ = new TLQConsumer(tlq_, txt_);
  }

  public void init() {
    add(txt_); // add text area to applet display
    add(startstop_);
    startstop_.setBackground(Color.red);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target == startstop_) {
      if (!started_) {
        startstop_.setBackground(Color.green);
        started_ = true;
        pt_ = new Thread(producer_);
        ct_ = new Thread(consumer_);
        pt_.start();
        ct_.start();
      }
      else {
        startstop_.setBackground(Color.red);
        started_ = false;
        pt_.stop();
        ct_.stop();
      }
      return true;
    }
    return false;
  }

}
