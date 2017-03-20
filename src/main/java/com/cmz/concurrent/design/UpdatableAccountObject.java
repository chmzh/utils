package com.cmz.concurrent.design;public class UpdatableAccountObject 
             implements UpdatableAccount{
  private long currentBalance_;

  public UpdatableAccountObject(long initialBalance) {
    currentBalance_ = initialBalance;
  }

  public synchronized long balance() { return currentBalance_; }

  public synchronized void credit(long amount)
   throws InsufficientFunds {
    if (amount >= 0 || currentBalance_ >= -amount)
      currentBalance_ += amount;
    else 
      throw new InsufficientFunds();
  }

  public synchronized void debit(long amount)
   throws InsufficientFunds {
    credit(-amount);
  }
}
