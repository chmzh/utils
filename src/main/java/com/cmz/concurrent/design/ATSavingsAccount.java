package com.cmz.concurrent.design;
public class ATSavingsAccount extends BankAccount {
  protected ATCheckingAccount checking_; // fixed after init
  protected long maxTransfer_; 
  public ATSavingsAccount(long maxTrnsfr) {
    maxTransfer_ = maxTrnsfr;
  }

 // call only upon initialization
  void initChecking(ATCheckingAccount c) { checking_ = c; }

  synchronized long transferOut() { // called only from checking
    long amount = balance_;
    if (amount > maxTransfer_) amount = maxTransfer_;
    if (amount < 0) amount = 0;
    balance_ -= amount;
    return amount;
  }

  public synchronized void deposit(long amount)
   throws InsufficientFunds {
    if (balance_ < -amount) 
      throw new InsufficientFunds();
    else {
      balance_ += amount;
      checking_.tryTransfer();
    }
  }
}
