package com.cmz.concurrent.design;import java.awt.*;

public class TransactionLogger {
  protected TextArea txt_;

  public TransactionLogger(TextArea txt) { txt_ = txt; }

  public void logTransfer(long amount, TransBankAccount src, 
                          TransBankAccount dst) {
    txt_.appendText("Transfer " + amount + /* " from " + 
                    src + " to " + dst + */ "\n");
  }

  public void logCredit(long amount, TransBankAccount dst) {
    txt_.appendText("Credit " + amount + /* " to " + dst + */ "\n");
  }


  public void cancelLogEntry(long amount, TransBankAccount src, 
                            TransBankAccount dst, Exception reason) {
    txt_.appendText("Cancel Transfer " + 
                    amount + /* " from " + src + " to " + dst + */
                    " Reason: " + reason + "\n");
  }

  public void cancelCredit(long amount, TransBankAccount dst, Exception reason) {
    txt_.appendText("Cancel Credit " + amount +
                    /* " to " + dst + */
                    " Reason: " + reason + "\n");

  }

}
