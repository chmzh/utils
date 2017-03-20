package com.cmz.concurrent.design;
import java.applet.*;
import java.awt.*;

public class ChangingTextApplet extends AutonomousGraphicApplet {
  protected AutonomousGraphic model_;

  protected AutonomousGraphic model() { return model_; }

  public void init() {
    Rectangle area = new Rectangle(10, 10, 200, 40);
    model_ = new ChangingText(this, area, "Hello");
  }



}
