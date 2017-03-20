package com.cmz.concurrent.design;

public class AccountUser { 
  TransactionLogger log_;

  public AccountUser(TransactionLogger log) { log_ = log; }

  public boolean credit(long amount, TransBankAccount acct) 
   throws FailedTransferException, RetryableTransferException { 
    Object key = new Object();
    log_.logCredit(amount, acct);
    try {
      acct.join(key);
      acct.deposit(key, amount);
      if (acct.canCommit(key)) {
        try {
          acct.commit(key);
          return true;
        }
        catch(KeyException k) { // commitment failure
          acct.abort(key);
          log_.cancelCredit(amount, acct, k);
          throw new FailedTransferException(); 
        }
      }
      else {
        acct.abort(key);
        log_.cancelCredit(amount, acct, new KeyException());
        throw new RetryableTransferException();
      }
    }
    // deal with semantic failures:
    catch (InsufficientFunds ex) { 
      acct.abort(key);
      log_.cancelCredit(amount, acct, ex);
      return false;
    }
    // all other transaction failures retryable
    catch (KeyException k) { 
      acct.abort(key);
      log_.cancelCredit(amount, acct, k);
      throw new RetryableTransferException();
    }
  }

  public boolean transfer(long amount, TransBankAccount source,
                          TransBankAccount destination)
   throws FailedTransferException, RetryableTransferException { 

    if (source == destination) return true; // avoid aliasing
    Object key = new Object();           // new transaction id
    log_.logTransfer(amount, source, destination); // record

    try {
      source.join(key);
      destination.join(key);
      source.withdraw(key, amount);
      destination.deposit(key, amount);
      // try to commit
      if (source.canCommit(key) && destination.canCommit(key)) {
        try {
          source.commit(key);
          destination.commit(key);
          return true;
        } 
        catch(KeyException k) { // commitment failure
          rollback(key, amount, source, destination, k);
          throw new FailedTransferException(); 
        }
      }

      else { // can retry later if cannot now commit
        rollback(key, amount, source, destination, new RetryableTransferException());
        throw new RetryableTransferException();
      }
    }
    // deal with semantic failures:
    catch (InsufficientFunds ex) { 
      rollback(key, amount, source, destination, ex);
      return false;
    }
    // all other transaction failures retryable
    catch (KeyException k) { 
      rollback(key, amount, source, destination, k);
      throw new RetryableTransferException();
    }
  }

  // helper method called on any failure
  void rollback(Object key, long amount, 
                TransBankAccount source,
                 TransBankAccount destination,
                Exception reason) {
    log_.cancelLogEntry(amount, source, destination, reason);
    source.abort(key);
    destination.abort(key);
  }
}


  
