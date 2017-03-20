package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class OptimAccountUserApplet extends Applet {
  static final int nThreads = 5;

  protected TransBankAccount acct1_;
  protected TransBankAccount acct2_;
  protected AccountUser     user_;
  protected TransferRunner[] runners_;
  protected Thread[] threads_;
  protected Button startstop_ = new Button("start/stop");
  protected boolean started_ = false;
  protected TextArea txt_;
  TransactionLogger log_;

  public OptimAccountUserApplet() {
    acct1_ = new OptimBankAccount();
    acct2_ = new OptimBankAccount();
  }

  public void init() {
    txt_ = new TextArea(8, 40);
    log_ = new TransactionLogger(txt_);
    user_ = new AccountUser(log_);

    Panel p1 = new Panel();
    runners_ = new TransferRunner[nThreads];
    threads_ = new Thread[nThreads];
    for (int i = 0; i < nThreads; ++i) {
      runners_[i] = new TransferRunner(user_, acct1_, acct2_);
      p1.add(runners_[i]);
    }
    add(p1);
    add(txt_);
    startstop_.setBackground(Color.red);
    add(startstop_);
    try {
      user_.credit(100, acct1_);
      user_.credit(100, acct2_);
    }
    catch (Exception ignore) {}
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

class TransferRunner extends java.awt.Label implements Runnable {
  static final long sleepTime = 10000;
  static final Color idleColor = Color.lightGray;
  static final Color errorColor = Color.red;
  static final Color okColor = Color.green;
  protected  AccountUser user_;
  protected  TransBankAccount acct1_;
  protected  TransBankAccount acct2_;

  TransferRunner(AccountUser u, TransBankAccount acct1, TransBankAccount acct2){
    super("           ");
    user_ = u; acct1_ = acct1; acct2_ = acct2;
    setBackground(idleColor);
  }

  public void run() {
    for (;;) {
      boolean success = false;
      setBackground(okColor);
      long amount = (long) ((Math.random() - 0.5) * 100);
      setText(" " + amount);
      try {
        success = user_.transfer(amount, acct1_, acct2_);
        if (!success)
          setBackground(errorColor);
      }
      catch (Exception ex) {
        setBackground(errorColor);
      }
      try {
        Thread.currentThread().sleep((long)(Math.random() * sleepTime));
      }
      catch(InterruptedException ex) {}
    }
  }
}



