package com.cmz.concurrent.design;public class TransferDaemon implements Runnable {
  static final long recheckInterval = 86400000; // 24 hrs

  protected BankAccount checking_;
  protected BankAccount savings_;
  protected long amountToTransfer_;
  protected Thread me_;

  public TransferDaemon(BankAccount checking, 
                        BankAccount savings,
                        long amount) {
    checking_ = checking;
    savings_ = savings;
    amountToTransfer_ = amount;
    me_ = new Thread(this);
    me_.setDaemon(true);
    me_.start();
  }

  protected void transfer() { /* do the transfer */ }

  protected void checkAndAct() {
    transfer();
  }

  public void run() {
    for (;;) {
      checkAndAct();
      try { Thread.sleep(recheckInterval); }
      catch (InterruptedException ex) { return; }
    }
  }
}

