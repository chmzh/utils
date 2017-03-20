package com.cmz.concurrent.design;public interface TransBankAccount extends Transactor {
  public long balance(Object key) throws KeyException;
  public void deposit(Object key, long amount) 
   throws InsufficientFunds, KeyException;
  public void withdraw(Object key, long amount)
   throws InsufficientFunds, KeyException;
}
