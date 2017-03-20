package com.cmz.concurrent.design;
public class BankAccount {
  protected long balance_ = 0;

  public synchronized long balance() { return balance_; }

  public synchronized void deposit(long amount)
   throws InsufficientFunds {
    if (balance_ < -amount) 
       throw new InsufficientFunds();
    else 
       balance_ += amount;
  }

  public void withdraw(long amount) throws InsufficientFunds {
    deposit(-amount);
  }
}
