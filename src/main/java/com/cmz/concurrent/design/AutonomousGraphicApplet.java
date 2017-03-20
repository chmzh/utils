package com.cmz.concurrent.design;
import java.awt.*;
import java.applet.*;

public abstract class AutonomousGraphicApplet extends Applet {
  protected abstract AutonomousGraphic model();

  public void start() { (new Thread(model())).start(); }

  public void paint(Graphics g) { model().paint(g); }
}
