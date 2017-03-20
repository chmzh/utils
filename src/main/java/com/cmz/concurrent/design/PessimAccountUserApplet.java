package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public class PessimAccountUserApplet extends OptimAccountUserApplet {
  public PessimAccountUserApplet() {
    acct1_ = new PessimBankAccount();
    acct2_ = new PessimBankAccount();
  }
}
