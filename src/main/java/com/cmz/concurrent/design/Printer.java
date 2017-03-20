package com.cmz.concurrent.design;
import java.awt.*;

public class Printer {
  protected TextArea txt_;  // The place to print things

  public Printer(TextArea txt) { txt_ = txt; }

  public void printDocument(String doc) { 
    txt_.appendText(doc);
  }

}


