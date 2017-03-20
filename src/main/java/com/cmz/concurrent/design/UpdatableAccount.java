package com.cmz.concurrent.design;public interface UpdatableAccount extends Account {
  public void credit(long amount) throws InsufficientFunds;
  public void debit(long amount) throws InsufficientFunds;
}
