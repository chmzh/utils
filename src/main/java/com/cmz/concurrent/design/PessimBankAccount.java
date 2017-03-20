package com.cmz.concurrent.design;
public class PessimBankAccount implements TransBankAccount {
  protected long balance_ = 0;
  protected Lock lock_ = new SimpleTimeOutLock(); // fixed
  protected long backupBalance_ = 0; // for rollback on abort

  public long balance(Object key) throws KeyException {
    lock_.enter(key);
    synchronized(this) { return balance_; }
  }


  public void deposit(Object key, long amount)
   throws InsufficientFunds, KeyException {
    lock_.enter(key);
    synchronized(this) { 
      if (balance_ < -amount) throw new InsufficientFunds();
      else balance_ += amount;
    }
  }


  public void withdraw(Object key, long amount)
   throws InsufficientFunds, KeyException {
    deposit(key, -amount);
  }


  public void join(Object key) throws KeyException { 
    lock_.acquire(key);
    synchronized(this) { backupBalance_ = balance_; }
  }

  public void abort(Object key) { 
    lock_.release(key);
    synchronized(this) { balance_ = backupBalance_; }
  }

  public boolean canCommit(Object key)  { 
    return lock_.hasKey(key);
  }

  public void commit(Object key) throws KeyException {
    lock_.release(key);
  }
}

