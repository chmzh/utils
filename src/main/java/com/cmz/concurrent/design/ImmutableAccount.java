package com.cmz.concurrent.design;
public final class ImmutableAccount // the adapter class
                   implements Account, Immutable {
  private Account delegate_;

  // create a fresh immutable account
  public ImmutableAccount(long initialBalance) {
    delegate_ = new UpdatableAccountObject(initialBalance);
  }

  // hold an existing account immutably
  ImmutableAccount(Account delegate) { delegate_ = delegate; }

  public long balance() { return delegate_.balance(); }
}
