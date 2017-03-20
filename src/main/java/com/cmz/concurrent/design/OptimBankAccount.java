package com.cmz.concurrent.design;
public class OptimBankAccount implements TransBankAccount {
  protected long balance_ = 0;
  protected long workingBalance_ = 0; // single shadow copy
  protected Object currentKey_ = null; // single transaction key

  public synchronized long balance(Object key) 
   throws KeyException {
    if (key != currentKey_) throw new KeyException();
    return workingBalance_;
  }

  public synchronized void deposit(Object key, long amount)
   throws InsufficientFunds, KeyException {
    if (key != currentKey_) throw new KeyException();
    if (workingBalance_ < -amount) throw new InsufficientFunds();
    workingBalance_ += amount;
  }

  public synchronized void withdraw(Object key, long amount)
   throws InsufficientFunds, KeyException {
    deposit(key, -amount);
  }


  public synchronized void join(Object key) throws KeyException {
    if (currentKey_ != null) throw new KeyException();
    currentKey_ = key;
    workingBalance_ = balance_;
  }

  public synchronized boolean canCommit(Object key) {
    return (key == currentKey_);
  }

  public synchronized void abort(Object key) {
    currentKey_ = null;
  }

  public synchronized void commit(Object key) 
   throws KeyException {
    if (key != currentKey_) throw new KeyException();
    balance_ = workingBalance_;
    currentKey_ = null;
  }

}

