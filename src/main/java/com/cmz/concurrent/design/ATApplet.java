package com.cmz.concurrent.design;

// demo for auto-transfer classes
// Note: This uses special hacked versions of these
// classes in order to demo the transfers

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;

public class ATApplet extends Applet {
  static final int nThreads = 2;

  protected DemoATSavingsAccount savings_;
  protected DemoATCheckingAccount checking_;
  protected DepositRunner[] savRunners_;
  protected DepositRunner[] chkRunners_;
  protected Thread[] st_;
  protected Thread[] ct_;
  protected Button startstop_ = new Button("start/stop");
  protected boolean started_ = false;


  public void init() {
    Label sl = new Label("Savings           ");
    Label cl = new Label("Checking          ");
    savings_ = new DemoATSavingsAccount(100, sl);
    checking_ = new DemoATCheckingAccount(100, cl);
    savings_.setChecking(checking_);
    checking_.setSavings(savings_);

    
    Panel p1 = new Panel();
    p1.add(sl);
    savRunners_ = new DepositRunner[nThreads];
    st_ = new Thread[nThreads];
    for (int i = 0; i < nThreads; ++i) {
      savRunners_[i] = new DepositRunner(savings_);
      p1.add(savRunners_[i]);
    }
    add(p1);
    Panel p2 = new Panel();
    p2.add(cl);
    chkRunners_ = new DepositRunner[nThreads];
    ct_ = new Thread[nThreads];
    for (int i = 0; i < nThreads; ++i) {
      chkRunners_[i] = new DepositRunner(checking_);
      p2.add(chkRunners_[i]);
    }
    add(p2);
    startstop_.setBackground(Color.red);
    add(startstop_);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target == startstop_) {
      if (!started_) {
        startstop_.setBackground(Color.green);
        started_ = true;
        for (int i = 0; i < nThreads; ++i) {
          st_[i] = new Thread(savRunners_[i]);
          ct_[i] = new Thread(chkRunners_[i]);
          st_[i].start();
          ct_[i].start();
        }
      }
      else {
        startstop_.setBackground(Color.red);
        started_ = false;
        for (int i = 0; i < nThreads; ++i) {
          st_[i].stop();
          ct_[i].stop();
        }

      }
      return true;
    }
    return false;
  }


}

class DepositRunner extends java.awt.Label implements Runnable {
  static final long sleepTime = 10000;
  static final Color idleColor = Color.lightGray;
  static final Color incColor = Color.orange;
  static final Color decColor = Color.orange;
  static final long  MAX = 100;
  protected  BankAccount acct_;
  static final String prefix = "Deposit: ";
  DepositRunner(BankAccount acct) {
    super(prefix + "        ");
    acct_ = acct;
    setBackground(idleColor);
  }

  public void run() {
    for (;;) {
      long amount = (long)((Math.random() - 0.60) * MAX);
      setBackground((amount >= 0)? incColor : decColor); 
      setText(prefix + amount);
      try {
        acct_.deposit(amount);
      }
      catch (InsufficientFunds ex) {
        setBackground(Color.red);
      }

      try {
        Thread.currentThread().sleep((long)(Math.random() * sleepTime));
      }
      catch(InterruptedException ex) {}
    }
  }
}




/* public */ class DemoATCheckingAccount extends BankAccount {
  static final Color idleColor = Color.lightGray;
  static final Color depositColor = Color.green;
  static final Color transferColor = Color.cyan; 
  protected DemoATSavingsAccount savings_ = null; // fixed
  protected long threshold_; 
  protected TSBoolean transferInProgress_; // fixed, unique
  Label lab_;
  static final String prefix = "Checking: ";

  public DemoATCheckingAccount(long threshold, Label l) {
    threshold_ = threshold;
    transferInProgress_ = new TSBoolean();
    lab_ = l;
    lab_.setBackground(idleColor);
  }

  void setSavings(DemoATSavingsAccount s) {
    savings_ = s; 
  }

 protected boolean shouldTry() { return balance_ < threshold_; }

 void tryTransfer() { // called internally or from savings
    if (!transferInProgress_.testAndSet()) { // if not busy ...
      synchronized(this) {
        if (shouldTry()) {
          long b = savings_.transferOut();
          balance_ += b;
          if (b != 0) {
            lab_.setText(prefix + balance_);
            lab_.setBackground(transferColor);
          }
        }
      }
      transferInProgress_.clear(); // allow other entries
    }
  }

  public synchronized void deposit(long amount)
   throws InsufficientFunds {
    if (balance_ < -amount) 
       throw new InsufficientFunds();
    else {
      balance_ += amount;
      lab_.setText(prefix + balance_);
      lab_.setBackground(depositColor);
      tryTransfer();
    }
  }
}

/*public*/ class DemoATSavingsAccount extends BankAccount {
  static final Color idleColor = Color.lightGray;
  static final Color depositColor = Color.green;
  static final Color transferColor = Color.cyan; 
  protected DemoATCheckingAccount checking_ = null; // fixed, unique
  protected long maxTransfer_; 
  Label lab_;
  static final String prefix = "Savings: ";
  public DemoATSavingsAccount(long maxTrnsfr, Label l) {
    maxTransfer_ = maxTrnsfr;
    lab_ = l;
    lab_.setBackground(idleColor);
  }

  void setChecking(DemoATCheckingAccount c) {
    checking_ = c; 
  }

  synchronized long transferOut() { // called only from checking
    long amount = balance_;
    if (amount > maxTransfer_) amount = maxTransfer_;
    if (amount < 0) amount = 0;
    balance_ -= amount;
    if (amount != 0) {
      lab_.setText(prefix + balance_);
      lab_.setBackground(transferColor);
    }
    return amount;
  }

  public synchronized void deposit(long amount)
   throws InsufficientFunds {
    if (balance_ < -amount) 
      throw new InsufficientFunds();
    else {
      balance_ += amount;
      lab_.setText(prefix + balance_);
      lab_.setBackground(depositColor);
      checking_.tryTransfer();
    }
  }
}
