package com.cmz.concurrent.design;
public class ATCheckingAccount extends BankAccount {
  protected ATSavingsAccount savings_; // fixed after init
  protected long threshold_; 
  protected TSBoolean transferInProgress_ = new TSBoolean();

  public ATCheckingAccount(long threshold) {
    threshold_ = threshold;
  }

 // call only upon initialization
  void setSavings(ATSavingsAccount s) { savings_ = s; }

 protected boolean shouldTry() { return balance_ < threshold_; }

 void tryTransfer() { // called internally or from savings
    if (!transferInProgress_.testAndSet()) { // if not busy ...
      synchronized(this) {
        if (shouldTry())
          balance_ += savings_.transferOut();
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
      tryTransfer();
    }
  }
}
