package com.cmz.concurrent.design;public interface Transactor {
  // enter a new transaction
  public void join(Object key) throws KeyException;

  // return true if this transaction can be committed
  public boolean canCommit(Object key);

  // update state to reflect current transaction
  public void commit(Object key) throws KeyException;

  // roll back state (No exception; ignore if inapplicable)
  public void abort(Object key);
}
